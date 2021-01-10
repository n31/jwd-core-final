package com.epam.jwd.core_final.exception;

public class NotUniqueEntityException extends RuntimeException{
    private String message;

    public NotUniqueEntityException() {
        this.message = "Entity is not unique";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
