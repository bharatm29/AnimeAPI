package com.bharat.AnimeAPI.security.controllers;

import com.bharat.AnimeAPI.security.models.AuthRegisterRequest;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthRegisterRequest registerUser(@RequestBody UserSaveWrapper userRegisterPayload){
        return authService.registerUser(userRegisterPayload);
    }
}