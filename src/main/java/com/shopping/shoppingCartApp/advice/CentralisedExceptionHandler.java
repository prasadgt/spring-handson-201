package com.shopping.shoppingCartApp.advice;

import com.shopping.shoppingCartApp.exceptions.ExceptionResponse;
import com.shopping.shoppingCartApp.exceptions.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class CentralisedExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> throwCustomExceptions(ResourceNotFoundException e) throws Exception{
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setErrorDescription(e.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generateGeneralExceptions(Exception e) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setErrorDescription(e.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
