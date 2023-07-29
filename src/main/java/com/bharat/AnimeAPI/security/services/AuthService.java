package com.bharat.AnimeAPI.security.services;

import com.bharat.AnimeAPI.exceptions.UserAlreadyExistsException;
import com.bharat.AnimeAPI.security.models.AuthRegisterRequest;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;


    public AuthRegisterRequest registerUser(UserSaveWrapper userPayload) throws UserAlreadyExistsException {
        String userEmail = userPayload.getEmail();
        if (userRepository.existsById(userEmail)) {
            throw new UserAlreadyExistsException(String.format("Email %s is already taken", userEmail));
        }

        userRepository.save(userPayload);

        return AuthRegisterRequest.builder()
                .message(
                        String.format(
                        "Registration for %s with email: %s successful",
                        userPayload.getUsername(),
                        userEmail)
                )
                .build();
    }
}
