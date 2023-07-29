package com.bharat.AnimeAPI.security.services;

import com.bharat.AnimeAPI.security.models.AuthRegisterRequest;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public AuthRegisterRequest registerUser(UserSaveWrapper userPayload){
        userRepository.save(userPayload);

        return AuthRegisterRequest.builder()
                .message("Registration Successful")
                .build();
    }
}
