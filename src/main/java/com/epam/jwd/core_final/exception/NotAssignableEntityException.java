package com.epam.jwd.core_final.exception;

public class NotAssignableEntityException extends RuntimeException {
    private String message;

    public NotAssignableEntityException() {
        this.message = "Entity is not able to be assigned";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
