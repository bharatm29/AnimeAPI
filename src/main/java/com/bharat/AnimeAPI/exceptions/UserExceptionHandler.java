package com.bharat.AnimeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistsException e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
