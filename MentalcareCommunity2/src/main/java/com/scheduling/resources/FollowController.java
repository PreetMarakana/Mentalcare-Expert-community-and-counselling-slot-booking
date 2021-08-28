package com.scheduling.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.FollowService;

@RestController
@RequestMapping("/secure/follow")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FollowController {

    @Autowired
    private FollowService followService;
    
    @PostMapping(value = "users/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity followUser(@PathVariable("userId") Long userId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (userId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        
        if (userDetails!=null) {
            String ans= followService.followUser(userId,userDetails.getUsername());
            if (!ans.equals("error"))  { 
                responseEntity.setData(ans);
                responseEntity.setStatus("success");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }else {
                responseEntity.setData("");
                responseEntity.setStatus("failed");
                responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
        }else {
            responseEntity.setData("");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
            }
    }
}