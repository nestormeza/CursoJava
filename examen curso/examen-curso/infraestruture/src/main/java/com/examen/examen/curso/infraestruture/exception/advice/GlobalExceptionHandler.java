package com.examen.examen.curso.infraestruture.exception.advice;

import com.examen.examen.curso.infraestruture.utils.dto.ResponseStandard;
import com.examen.examen.curso.infraestruture.exception.SunatCompanyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SunatCompanyException.class)
    public ResponseEntity<ResponseStandard<Object>> handleSunatCompanyException(SunatCompanyException ex) {
        int code = ex.getCode();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseStandard<>(code, ex.getMessage(), null));
    }
}
