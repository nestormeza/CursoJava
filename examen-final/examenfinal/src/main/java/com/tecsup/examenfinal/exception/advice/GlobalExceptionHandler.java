package com.tecsup.examenfinal.exception.advice;

import com.tecsup.examenfinal.exception.GlobalException;
import com.tecsup.examenfinal.utils.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ResponseException> handlerGlobalException(GlobalException exception){
        int code = exception.getCode();
        HttpStatus status = HttpStatus.resolve(code);

        //Si el codigo es incorrecto tomara por defecto el error 500
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(status).body(new ResponseException(code,exception.getMessage()));
    }
}
