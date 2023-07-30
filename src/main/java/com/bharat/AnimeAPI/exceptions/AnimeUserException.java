package com.bharat.AnimeAPI.exceptions;

public class AnimeUserException extends Exception{
    private String message;

    public AnimeUserException(String message) {
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
