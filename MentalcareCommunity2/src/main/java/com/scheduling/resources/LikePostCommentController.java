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
import com.scheduling.services.LikePostCommentService;

@RestController
@RequestMapping("/secure/commentlike/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikePostCommentController {

    @Autowired
    private LikePostCommentService likePostCommentService;
    
    @PostMapping(value = "postComments/{postCommentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity likePostComment(@PathVariable("postCommentId") Long commentId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (userDetails!=null) {
            String res = likePostCommentService.createLike(commentId,userDetails.getUsername());
            if (!res.equals("")) {
                responseEntity.setData(res);
                responseEntity.setHttpStatus(HttpStatus.OK);
                responseEntity.setStatus("success");
                return responseEntity;
            }
        }
        responseEntity.setData("not done");
        responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseEntity.setStatus("not success");
        return responseEntity;
    }
}
