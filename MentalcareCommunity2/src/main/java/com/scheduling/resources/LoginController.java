package com.scheduling.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.ChangePasswordDto;
import com.scheduling.dto.FacebookLoginRequest;
import com.scheduling.dto.OtpDto;
import com.scheduling.dto.UserDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.security.Jwtutil;
import com.scheduling.serviceImplimentation.OtpAttemptService;
import com.scheduling.services.EmailService;
import com.scheduling.services.OtpService;
import com.scheduling.services.UserServices;

@RestController
@RequestMapping("/login/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

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
    
    @Autowired
    private Jwtutil jwtutil;
    
    static final String SUCCESS = "success";
    
    @GetMapping("/login")
    @CrossOrigin
    public ResponseEntity loginpage() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData("Enter token for access");
        responseEntity.setHttpStatus(HttpStatus.UNAUTHORIZED);
        return responseEntity;
    }
    
    @GetMapping("/logout")
    @CrossOrigin
    public ResponseEntity logout() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData("logoutSuccess");
        responseEntity.setHttpStatus(HttpStatus.OK);
        
        responseEntity.setStatus(SUCCESS);
        return responseEntity;
    }

    @GetMapping("/loginattemtfailed")
    @CrossOrigin
    public ResponseEntity loginattemtfailed() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setData("invalid username and passwords");
        responseEntity.setStatus("failed");
        return responseEntity;
    }
    
    @GetMapping("/loginsuccess")
    @CrossOrigin
    public ResponseEntity loginsuccess(@RequestParam("username") String  username) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatus(SUCCESS);
        Long userId = userService.getUseridUsingEmail(username);
        responseEntity.setData(userId);
        responseEntity.setHttpStatus(HttpStatus.OK);
        responseEntity.setToken(jwtutil.generateToken(username));
        return responseEntity;
    }
     
    @PostMapping(value = "/facebooklogin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity facebookLogin(@RequestBody FacebookLoginRequest loginrequest,BindingResult results) {
        ResponseEntity responseEntity = new ResponseEntity();
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "accessToken", "can not be empty.");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        String tokenString = userService.getTokenOrRegisterFacebookLogin(loginrequest);
        if (tokenString!=null) {
            responseEntity.setData("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            responseEntity.setToken(tokenString);
            return responseEntity;
        }else {
            responseEntity.setData("fail");
            responseEntity.setHttpStatus(HttpStatus.OK);
            responseEntity.setToken("");
            return responseEntity;
        }
    }
        
    
    @CrossOrigin
    @PostMapping(value = "/forgetpassword")
    public ResponseEntity verifyIndetityAndSendMail(@RequestParam("username") String username) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (!username.isEmpty()) {
            UserDto userDto = userService.getUserByEmailForDto(username);
            if (userDto!=null) {
                OtpDto otp = otpService.createOtp(userDto.getUserId(),null);
                responseEntity.setData(userDto.getUserId());
                emailService.sendmail(userDto.getUserEmail(), otp.getOtpValue());
                responseEntity.setStatus("otpdone");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }else {
                responseEntity.setStatus("Enter Register Email");
                responseEntity.setHttpStatus(HttpStatus.NOT_FOUND);
                return responseEntity;
            }
        }else {
            responseEntity.setStatus("Enter Register Email");
            responseEntity.setHttpStatus(HttpStatus.NOT_FOUND);
            return responseEntity;
        }
    }
    @PostMapping(value = "/forgotpass/otpverify",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity otpverify(@RequestBody OtpDto otpDto,BindingResult results) {
        
        ResponseEntity responseEntity  = new ResponseEntity();
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
        String username =  otpService.verifyOtpForForget(otpDto.getOtpValue());
       if (!username.isEmpty()) {
           otpAttemptService.otpSucceeded(getClientIP());
           responseEntity.setData("otp successfully verify");
           responseEntity.setHttpStatus(HttpStatus.OK);
           responseEntity.setToken(jwtutil.generateToken(username));
           return responseEntity;
       }else {
           otpAttemptService.otpFailed(getClientIP());
           responseEntity.setData("Otp is incorrect");
           responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
           return responseEntity;
       }
       
    }
    
    @GetMapping(value = "/forgotpass/resend",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity resendotp(@RequestParam("key") long uid) {
        
        ResponseEntity responseEntity = new ResponseEntity();
        try {
            if (otpService.resendOtp(uid)) {
                otpAttemptService.otpSucceeded(getClientIP());
                responseEntity.setHttpStatus(HttpStatus.OK);
                responseEntity.setStatus("otp resend");
                return responseEntity; 
            }else {
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
    
    @PostMapping(value = "/changepass",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto changePasswordDto,BindingResult results,@AuthenticationPrincipal UserDetails details) {
        ResponseEntity responseEntity = new ResponseEntity();
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "password", "password can not be empty.");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "email", "email not be empty.");
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
       if (details!=null && userService.changePassword(changePasswordDto,details.getUsername())) {
           responseEntity.setData("done");
           responseEntity.setHttpStatus(HttpStatus.OK);
           responseEntity.setStatus("success");
           return responseEntity;
       }else {
           responseEntity.setData("not done");
           responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
           responseEntity.setStatus("failed");
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
