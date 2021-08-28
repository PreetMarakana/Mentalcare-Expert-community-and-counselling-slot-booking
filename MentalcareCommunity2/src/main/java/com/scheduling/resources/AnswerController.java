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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.AnswerDto;
import com.scheduling.dto.AnswerResponseDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.AnswerService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    
    @PostMapping(value = "/secure/answer/questions/{questionId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAnswer(@PathVariable("questionId") Long qid ,@RequestBody AnswerDto answerDto,BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (qid == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "answer","answer is requied");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        if (userDetails!=null) {
            AnswerResponseDto answerResponseDto = answerService.createAnswer(qid,answerDto,userDetails.getUsername()); 
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
    
    @DeleteMapping(value = "/secure/answers/{answerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAnswer(@PathVariable("answerId") Long aid,@AuthenticationPrincipal UserDetails userDetails ) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (aid == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        if (userDetails!=null) {
           if(answerService.deleteAnswer(userDetails.getUsername(),aid)) {
               responseEntity.setData("done");
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
