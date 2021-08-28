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
import com.scheduling.services.LikeAnswerService;

@RestController
@RequestMapping("/secure/like/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeAnswerController {

    @Autowired
    private LikeAnswerService likeAnswerService;
    
    @PostMapping(value = "answers/{answerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity likeAnswer(@PathVariable("answerId") Long answerId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (answerId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        if (userDetails!=null) {
            String ans = likeAnswerService.likeAnswer(answerId,userDetails.getUsername());
            if (!ans.equals("error")) {
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
