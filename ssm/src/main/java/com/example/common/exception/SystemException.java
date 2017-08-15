package com.example.common.exception;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -6814852588878051200L;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}