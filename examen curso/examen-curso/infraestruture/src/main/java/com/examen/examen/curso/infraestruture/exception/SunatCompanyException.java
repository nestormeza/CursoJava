package com.examen.examen.curso.infraestruture.exception;

public class SunatCompanyException extends RuntimeException {
    private final int code;
    public SunatCompanyException(int code,String message) {
        super(message);
        this.code = code;
    }
    public SunatCompanyException(int code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
