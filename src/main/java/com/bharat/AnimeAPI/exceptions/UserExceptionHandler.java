package com.bharat.AnimeAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistsException e){
        return new ResponseEntity<>(
                e.getMessage(),
                new MultiValueMapAdapter<>(
                        Map.of(
                            "Content-type", List.of("application/json"))
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AnimeUserException.class)
    public ResponseEntity<String> handleAnimeUserException(AnimeUserException e){
        return new ResponseEntity<>(
                e.getMessage(),
                new MultiValueMapAdapter<>(
                        Map.of(
                                "Content-type", List.of("application/json"))
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
