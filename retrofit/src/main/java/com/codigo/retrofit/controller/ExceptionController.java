package com.codigo.retrofit.controller;


import com.codigo.retrofit.exception.ConsultReniecException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleExceptionReniecException(ConsultReniecException e){
        Map<String,String> errorBody=new HashMap<>();
        errorBody.put("error","RENIEC ERROR");
        errorBody.put("message",e.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
