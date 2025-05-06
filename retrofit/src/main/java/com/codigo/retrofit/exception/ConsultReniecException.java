package com.codigo.retrofit.exception;

public class ConsultReniecException extends RuntimeException {
     public ConsultReniecException(String message) {
         super(message);
     }
     public ConsultReniecException(String message, Throwable cause) {
         super(message, cause);
     }
}
