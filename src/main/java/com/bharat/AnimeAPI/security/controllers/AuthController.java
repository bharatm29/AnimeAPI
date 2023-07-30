package com.bharat.AnimeAPI.security.controllers;

import com.bharat.AnimeAPI.exceptions.AnimeUserException;
import com.bharat.AnimeAPI.exceptions.UserAlreadyExistsException;
import com.bharat.AnimeAPI.security.models.AuthRegisterRequest;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.services.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Hidden
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthRegisterRequest registerUser(@RequestBody UserSaveWrapper userRegisterPayload) throws UserAlreadyExistsException, AnimeUserException {
        return authService.registerUser(userRegisterPayload);
    }
}