package com.bharat.AnimeAPI.exceptions;

public class UserAlreadyExistsException extends Exception{
    private String message;

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
