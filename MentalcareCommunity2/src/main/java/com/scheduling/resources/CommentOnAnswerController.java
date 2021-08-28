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

import com.scheduling.dto.AnswerCommentDto;
import com.scheduling.dto.CommentOnAnswerResponseDto;
import com.scheduling.dto.CommentResponseDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.CommetOnAnswerService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentOnAnswerController {

    @Autowired
    private CommetOnAnswerService commentAnswer;
    
    @PostMapping(value = "/secure/answerComments/answers/{answerId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createcommentOnAnswer(@RequestBody AnswerCommentDto answerCommentDto,BindingResult results,@PathVariable("answerId") Long answerId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        
        if (answerId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "comment","comment is requied");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        if (userDetails!=null) {
            
            CommentOnAnswerResponseDto answerResponseDto =  commentAnswer.createAnswercomment(answerCommentDto,answerId,userDetails.getUsername());
            
            if (answerResponseDto!=null) {
                responseEntity.setData(answerResponseDto);
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
    
    @DeleteMapping(value = "/secure/answerComments/{commentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteComment(@PathVariable("commentId") Long commentId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (commentId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        
        if (userDetails!=null) {
            if (commentAnswer.deleteCommment(commentId,userDetails.getUsername())) {
                responseEntity.setData("deleted");
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
    
    @GetMapping(value = "/commentAnswer/answers/{answerId}")
    public ResponseEntity getAllCommentsOfPost(@PathVariable("answerId") Long answerId,@AuthenticationPrincipal UserDetails details) {
        ResponseEntity responseEntity = new ResponseEntity();
        List<CommentResponseDto>  commentResponseDtos =  commentAnswer.getAllCommentsOfAnswer(answerId,details);
        responseEntity.setData(commentResponseDtos);
        responseEntity.setStatus("done");
        responseEntity.setHttpStatus(HttpStatus.OK);
        return responseEntity;
    }
}
