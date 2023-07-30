package com.bharat.AnimeAPI.security.services;

import com.bharat.AnimeAPI.exceptions.AnimeUserException;
import com.bharat.AnimeAPI.exceptions.UserAlreadyExistsException;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("While testing AuthService")
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    private AuthService underTest;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        underTest = new AuthService(userRepository);
    }

    @Test
    @DisplayName("testing registerUser method")
    void testingRegisterUser() throws UserAlreadyExistsException, AnimeUserException {
        //given
        String userEmail = "user@123";
        UserSaveWrapper user = UserSaveWrapper.builder()
                .username("user")
                .password("password")
                .email(userEmail)
                .build();
        given(userRepository.existsById(anyString())).willReturn(false);

        //when
        underTest.registerUser(user);

        //then
        ArgumentCaptor<UserSaveWrapper> argsCaptor = ArgumentCaptor.forClass(UserSaveWrapper.class);

        verify(userRepository).save(argsCaptor.capture());

        UserSaveWrapper capturedUser = argsCaptor.getValue();

        assertThat(user).isEqualTo(capturedUser);
    }

    @Test
    @DisplayName("testing if registerUser throws an UserAlreadyExistException")
    void registerUserWillThrowException(){
        //given
        String userEmail = "user@123";
        UserSaveWrapper user = UserSaveWrapper.builder()
                .username("user")
                .password("password")
                .email(userEmail)
                .build();

        given(userRepository.existsById(userEmail)).willReturn(true);

        //when
        //then
        assertThatThrownBy(
                () -> underTest.registerUser(user)
        )
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining(String.format("Email %s is already taken", userEmail));
    }

    @Test
    @DisplayName("testing if registerUser throws an AnimeUserException")
    void registerUserWillThrowAnimeUserException(){
        //given
        String userEmail = "user@123";
        UserSaveWrapper user = UserSaveWrapper.builder()
                .username("user")
                .email(userEmail)
                .build();

        //when
        //then
        assertThatThrownBy(
                () -> underTest.registerUser(user)
        )
                .isInstanceOf(AnimeUserException.class)
                .hasMessageContaining(String.format("Incomplete information, must supply all the three fields"));
    }
}