package com.examen.ms_productos.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalException extends RuntimeException{
    private final int code;

    public GlobalException(int code,String message){
        super(message);
        this.code=code;
    }

    public GlobalException(int code,String message,Throwable cause){
        super(message,cause);
        this.code= code;
    }
}
