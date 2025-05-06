package com.Tecsup.clase1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> ExceptionController(Exception exception) {
        Map<String,String> errorBody = new HashMap<>();
        errorBody.put("error", "Error de reniec");
        errorBody.put("message", exception.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
