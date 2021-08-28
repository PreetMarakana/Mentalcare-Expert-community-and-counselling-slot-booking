package com.scheduling.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.CommentOnPostDto;
import com.scheduling.dto.CommentOnPostResponseDto;
import com.scheduling.dto.CommentResponseDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.CommentOnPostService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentOnPostController {
    
    @Autowired
    private CommentOnPostService commentOnPostService;
    
    @PostMapping(value = "/secure/postcomment/posts/{postId}")
    public ResponseEntity createCommentOnPost(@PathVariable("postId") Long postId,@RequestBody CommentOnPostDto commentOnPostDto,BindingResult results,@AuthenticationPrincipal UserDetails userDetails){
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (postId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }    
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "comment","comment is required");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        if (userDetails!=null) {
            
            CommentOnPostResponseDto commentOnPostResponseDto = commentOnPostService.createcommentOnPost(postId,commentOnPostDto,userDetails.getUsername());
       
            if (commentOnPostResponseDto!=null) {
                responseEntity.setData(commentOnPostResponseDto);
                responseEntity.setStatus("done");
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
    
    @DeleteMapping(value = "/secure/postcomments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (commentId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }  
        if (userDetails!=null) {
            if (commentOnPostService.deletecommentOnPost(commentId,userDetails.getUsername())) {
                responseEntity.setData("deleted");
                responseEntity.setStatus("done");
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
    
    @GetMapping(value = "/postcomment/posts/{postId}")
    public ResponseEntity getAllCommentsOfPost(@PathVariable("postId") Long postId,@AuthenticationPrincipal UserDetails details) {
        ResponseEntity responseEntity = new ResponseEntity();
        List<CommentResponseDto>  commentResponseDtos =  commentOnPostService.getAllCommentsOfPost(postId,details);
        responseEntity.setData(commentResponseDtos);
        responseEntity.setStatus("done");
        responseEntity.setHttpStatus(HttpStatus.OK);
        return responseEntity;
    }
    
    
}
