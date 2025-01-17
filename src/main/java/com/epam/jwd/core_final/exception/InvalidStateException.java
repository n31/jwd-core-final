package com.epam.jwd.core_final.exception;

public class InvalidStateException extends Exception {
    private String message;

    public InvalidStateException() {
        this.message = "Invalid state exception";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
