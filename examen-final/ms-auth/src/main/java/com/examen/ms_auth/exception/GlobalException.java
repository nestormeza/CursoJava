package com.examen.ms_auth.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{
    private final int code;
    public GlobalException(int code, String message){
        super(message);
        this.code = code;
    }

    public GlobalException(int code, String message, Throwable cause){
        super(message,cause);
        this.code = code;
    }
}
