package com.scheduling.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.PostDto;
import com.scheduling.dto.PostResponseDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.PostService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostService postService;
    
    @PostMapping(value = "/secure/posts",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.ALL_VALUE)
    public ResponseEntity createPost(PostDto postDto,BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "files","at least one file required");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        if (userDetails!=null) {
           PostResponseDto postResponseDto =  postService.createPost(postDto,userDetails.getUsername());
           if (postResponseDto!=null) {
               responseEntity.setData(postResponseDto);
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
    
    @DeleteMapping(value = "/secure/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long pid,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (pid == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        
        if (userDetails!=null) {
            if (postService.deletePost(pid,userDetails.getUsername())) {
                responseEntity.setData("success");
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
}
