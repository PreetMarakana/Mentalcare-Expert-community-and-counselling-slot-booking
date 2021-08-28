package com.scheduling.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.CommentPostRepliesRequestDto;
import com.scheduling.dto.CommentRepliesDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.CommentOnPostReplieService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentOnPostReplieController {

    @Autowired
    private CommentOnPostReplieService commentOnPostReplieService;
    
    @PostMapping(value = "/secure/commentPostReply/commentPosts/{postCommentId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postReplyOnComment(@PathVariable("postCommentId") Long commentId,@RequestBody CommentPostRepliesRequestDto commentPostRepliesRequestDto,BindingResult results,@AuthenticationPrincipal UserDetails userDetails){
      
        ResponseEntity responseEntity = new ResponseEntity();
        if (commentId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "comment","can't be empty");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        if (userDetails!=null) {
            if (commentOnPostReplieService.createComment(commentId,commentPostRepliesRequestDto,userDetails.getUsername())) {
                responseEntity.setData("done");
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
    
    @DeleteMapping(value = "/secure/postCommentreplys/{commentReplyId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteReplyOnComment(@PathVariable("commentReplyId") Long replyId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity  = new ResponseEntity();
        
        if (userDetails!=null) {
            if (commentOnPostReplieService.deleteCommentReply(replyId,userDetails.getUsername())) {
                responseEntity.setData("done");
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
    
    @GetMapping(value = "/commentPosts/{commentsId}")
    public ResponseEntity getCommentsReplies(@PathVariable("commentsId") Long commentsId,@AuthenticationPrincipal UserDetails userDetails) {
        
        ResponseEntity responseEntity = new ResponseEntity();
        if (commentsId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        List<CommentRepliesDto>  commentResponseDtos =  commentOnPostReplieService.getcommentsreplies(commentsId,userDetails);
        responseEntity.setData(commentResponseDtos);
        responseEntity.setStatus("done");
        responseEntity.setHttpStatus(HttpStatus.OK);
        return responseEntity;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
