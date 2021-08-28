package com.scheduling.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.scheduling.responses.ResponseEntity;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity exception(UserExistException exception) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setHttpStatus(HttpStatus.IM_USED);
        return responseEntity;
    }
    
    @ExceptionHandler
    public ResponseEntity sqlexception(SqlQueryException sqlQueryException) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseEntity.setData("due to sql");
        return responseEntity;
    }
    
    @ExceptionHandler
    public ResponseEntity UserNotExists(UserNotExists userNotExists) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setHttpStatus(HttpStatus.NOT_FOUND);
        responseEntity.setData("user not exists");
        return responseEntity;
    }
    
    @ExceptionHandler
    public ResponseEntity LoginException(LoginException loginException) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setHttpStatus(HttpStatus.BAD_REQUEST);
        responseEntity.setData("Try again after 10 minutes");
        return responseEntity;
    }
}
