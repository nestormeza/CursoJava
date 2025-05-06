package com.Tecsup.clase1.exception;

public class ReniecException extends RuntimeException{
    public ReniecException(String message) {
        super(message);
    }
    public ReniecException(String message, Throwable cause) {
        super(message, cause);
    }
}
