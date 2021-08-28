package com.scheduling.resources;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.FeedsDto;
import com.scheduling.dto.MobileTokenDto;
import com.scheduling.dto.NotificationResponseDto;
import com.scheduling.dto.ReportDto;
import com.scheduling.dto.UserDetailsUpdate;
import com.scheduling.dto.UserProfileDto;
import com.scheduling.model.Updateprofile;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.CommunityDashboardService;
import com.scheduling.services.NotificationService;
import com.scheduling.services.UserServices;

@RestController
@RequestMapping("/community/dashboard/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommunityDashboardController {

    @Autowired
    private CommunityDashboardService communityDashboardService;
    
    @Autowired
    private UserServices userServices;
    
    @Autowired
    private NotificationService notificationService;
    
    @GetMapping(value = "feeds",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loadsFeeds(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("page") Integer pageNo) {
        ResponseEntity responseEntity = new ResponseEntity();
       Set<FeedsDto> feedsDtos =  communityDashboardService.getFeeds(userDetails,pageNo);
            responseEntity.setData(feedsDtos);
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        
    }
    @GetMapping(value = "userprofile/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userProfile(@PathVariable("userId") Long userId ,@RequestParam("page") Integer pageNo,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (userId!=null) {
            UserProfileDto userProfileDto =  communityDashboardService.getuserProfile(userId,userDetails,pageNo); 
            if (userProfileDto!=null) {
                responseEntity.setData(userProfileDto);
                responseEntity.setStatus("success");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }
        }
        responseEntity.setData("not done");
        responseEntity.setStatus("failed");
        responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
    
    @PutMapping(value = "/secure/userprofile",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUserProfile(@RequestBody UserDetailsUpdate detailsUpdate,BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "username","username is requied");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        if (userServices.updateUser(detailsUpdate,userDetails)) {
            responseEntity.setData("update successfully");
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("update failed");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
    
    @PutMapping(value = "/secure/userprofile/photo",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.ALL_VALUE)
    public ResponseEntity updateProfile(Updateprofile updateprofile,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if(userServices.updateUserPhoto(updateprofile, userDetails)) {
            responseEntity.setData("update successfully");
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("update failed");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
    
    @PutMapping(value = "/secure/user/mobileToken",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProfile(@RequestBody MobileTokenDto tokenDto,BindingResult result,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(result, "token", "Token can not be empty");
        
        if (result.hasErrors()) {
            responseEntity.setData(result.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        if(userServices.updateToken(tokenDto,userDetails)) {
            responseEntity.setData("update successfully");
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("update failed");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }
    
    @GetMapping(value = "/secure/notification",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserNotication(@AuthenticationPrincipal UserDetails details) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (details!=null) {
            List<NotificationResponseDto> list = notificationService.getUserNotification(details.getUsername());
            responseEntity.setData(list);
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
        }else {
            responseEntity.setData("token not valid");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    
    @PostMapping(value = "/secure/report",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity report(@RequestBody ReportDto reportDto,BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        
        ResponseEntity responseEntity = new ResponseEntity();
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "type","which type of post requied");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "user","username is requied");
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "id","post id is requied");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        userServices.submitReport(reportDto,userDetails);
            responseEntity.setData("update successfully");
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        
    }
    
    
}













