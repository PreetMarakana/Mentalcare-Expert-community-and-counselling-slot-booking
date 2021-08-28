package com.scheduling.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.Exception.UserExistException;
import com.scheduling.dto.OtpDto;
import com.scheduling.dto.UserDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.serviceImplimentation.OtpAttemptService;
import com.scheduling.services.EmailService;
import com.scheduling.services.OtpService;
import com.scheduling.services.UserServices;

@RestController
@RequestMapping("/registration/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRegistrationController {

    @Autowired
    private UserServices userService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpAttemptService otpAttemptService;

    @Autowired
    private HttpServletRequest request;

    @CrossOrigin
    @PostMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userRegistration(@RequestBody UserDto userDto, BindingResult results) {

        ResponseEntity responseEntity = new ResponseEntity();

        ValidationUtils.rejectIfEmptyOrWhitespace(results, "userName", "Name can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "userEmail", "email can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "userPassword", "password can not be empty.");

        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        userDto.setUserEmail(userDto.getUserEmail().toLowerCase());
        UserDto dto = userService.getUserByEmailForDto(userDto.getUserEmail());
        if (dto != null) {
            if (dto.isUserVerify()) {
                responseEntity.setException(new UserExistException("user already exist"));
                responseEntity.setHttpStatus(HttpStatus.FOUND);
                responseEntity.setData("user already exist");
                return responseEntity;
            } else {
                OtpDto otpDto = otpService.createOtp(dto.getUserId(),userDto);
                emailService.sendmail(dto.getUserEmail(),otpDto.getOtpValue());
                responseEntity.setHttpStatus(HttpStatus.OK);
                responseEntity.setData(dto.getUserId());
                responseEntity.setStatus("Email send verify by otp");
                return responseEntity;
            }
        }

        try {
            long userId = userService.createUser(userDto);
            OtpDto otpDto = otpService.createOtp(userId,null);
            emailService.sendmail(userDto.getUserEmail(), otpDto.getOtpValue());

            responseEntity.setHttpStatus(HttpStatus.OK);
            responseEntity.setData(userId);
            responseEntity.setStatus("Email send verify by otp");

            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseEntity.setData("try again");
            return responseEntity;

        }
    }

    @PostMapping(value = "otpverify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity otpverify(@RequestBody OtpDto otpDto, BindingResult results) {

        ResponseEntity responseEntity = new ResponseEntity();
        if (otpAttemptService.isOtpAttemptExceed(getClientIP())) {
            responseEntity.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseEntity.setStatus("limit-exceed");
            responseEntity.setData("try to resend otp");
            return responseEntity;
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "otpValue", "otp can not be empty.");

        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }

        if (otpService.verifyOtp(otpDto.getOtpValue())) {
            otpAttemptService.otpSucceeded(getClientIP());
            responseEntity.setData("otp successfully verify");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        } else {
            otpAttemptService.otpFailed(getClientIP());
            responseEntity.setData("Otp is incorrect");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }

    }

    @GetMapping(value = "resend", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity resendotp(@RequestParam("key") long uid) {

        ResponseEntity responseEntity = new ResponseEntity();
        try {
            if (otpService.resendOtp(uid)) {
                otpAttemptService.otpSucceeded(getClientIP());
                responseEntity.setHttpStatus(HttpStatus.OK);
                responseEntity.setStatus("otp resend");
                return responseEntity;
            } else {
                responseEntity.setHttpStatus(HttpStatus.NOT_FOUND);
                responseEntity.setStatus("key not valid");
                return responseEntity;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            responseEntity.setStatus("try after some time");
            return responseEntity;
        }

    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
