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

import com.scheduling.dto.QuestionDto;
import com.scheduling.dto.QuestionResponseDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.QuestionService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @PostMapping(value = "/secure/questions",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createQuestion(@RequestBody QuestionDto questionDto,BindingResult results,@AuthenticationPrincipal UserDetails userDetails) {
        
        ResponseEntity responseEntity = new ResponseEntity();
        
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "question","question is requied");
        
        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        
        if (userDetails!=null) {
            QuestionResponseDto questionDto2 =  questionService.createQuestion(questionDto,userDetails.getUsername());
          if (questionDto2!=null) {
              responseEntity.setData(questionDto2);
              responseEntity.setStatus("success");
              responseEntity.setHttpStatus(HttpStatus.OK);
              return responseEntity;
        }else {
            responseEntity.setData("");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }}else {
            responseEntity.setData("");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }  
    }
    
    @DeleteMapping(value = "/secure/question/{questionId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteQuestion(@PathVariable("questionId") Long questionId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (questionId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        if (userDetails!=null) {
            if(questionService.checkQuestionUser(userDetails.getUsername(),questionId)) {
                responseEntity.setData("done");
                responseEntity.setStatus("success");
                responseEntity.setHttpStatus(HttpStatus.OK);
                return responseEntity;
            }else {
                responseEntity.setData("");
                responseEntity.setStatus("failed");
                responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }}else {
                responseEntity.setData("");
                responseEntity.setStatus("failed");
                responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }  
    }
    
    @GetMapping(value = "/questions/{questionId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getQuestion(@PathVariable("questionId") Long questionId,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (questionId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        QuestionResponseDto questionResponseDto = questionService.getQuestionByIdDto(questionId,userDetails);
        if (questionResponseDto!=null) {
            responseEntity.setData(questionResponseDto);
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        
    }
    
    @GetMapping(value = "questiondashboard",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDashBoardQuestionList() {
        ResponseEntity responseEntity = new ResponseEntity();
        
        List<QuestionResponseDto> questionResponseDtos = questionService.getQuestionList();
        
        if (questionResponseDtos!=null) {
            responseEntity.setData(questionResponseDtos);
            responseEntity.setStatus("success");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }else {
            responseEntity.setData("");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
        
    }
    
}
