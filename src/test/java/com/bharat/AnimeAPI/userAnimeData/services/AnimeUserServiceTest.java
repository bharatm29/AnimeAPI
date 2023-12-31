package com.bharat.AnimeAPI.userAnimeData.services;

import com.bharat.AnimeAPI.animeInfo.services.AnimeInfoService;
import com.bharat.AnimeAPI.exceptions.AnimeUserException;
import com.bharat.AnimeAPI.security.repositories.UserRepository;
import com.bharat.AnimeAPI.userAnimeData.models.AnimeUser;
import com.bharat.AnimeAPI.userAnimeData.repositories.AnimeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("While testing AnimeUserService")
@ExtendWith(MockitoExtension.class)
class AnimeUserServiceTest {
    @Mock
    private AnimeUserRepository animeUserRepository;

    @Mock
    private AnimeInfoService animeInfoService;

    @Mock
    private UserRepository userRepository;

    private AnimeUserService underTest;

    @BeforeEach
    void setUp() {
        underTest = spy(new AnimeUserService(animeUserRepository, animeInfoService, userRepository));
    }

    @Nested
    @DisplayName("While testing addAnimeUser method")
    class testingAddAnimeUser{
        @Test
        @DisplayName("Testing if user not being registered, it will throw exception")
        void addAnimeUserThrowExceptionOnUserNotRegister() throws AnimeUserException {
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(true);

            //when
            //then
            assertThatThrownBy(
                    () -> underTest.addAnimeUser(user)
            )
                    .isInstanceOf(AnimeUserException.class)
                    .hasMessageContaining("User is not registered");
        }

        @Test
        @DisplayName("Testing if user is already initialized, it will throw exception")
        void addAnimeUserThrowExceptionOnUserAlreadyInitialized(){
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(animeUserRepository.existsById(anyString())).willReturn(true);
            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(false);

            //when
            //then
            assertThatThrownBy(
                    () -> underTest.addAnimeUser(user)
            )
                    .isInstanceOf(AnimeUserException.class)
                    .hasMessageContaining("User already initialized");
        }

        @Test
        @DisplayName("Testing if the user is being saved when all the ifs are passed")
        void addAnimeUserWillSaveTheObjectSuccessfullyOnConditionSatisfied() throws AnimeUserException {
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(false);
            given(animeUserRepository.existsById(anyString())).willReturn(false);

            //when
            underTest.addAnimeUser(user);

            //then
            verify(animeUserRepository).save(user);
        }
    }

    @Nested
    @DisplayName("While testing updateAnimeUsers method")
    class testingUpdateAnimeUsers{
        @Test
        @DisplayName("Testing if method throws exception if the user is not registered")
        void testingIfItThrowsException(){
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(true);

            //when
            //then
            assertThatThrownBy(
                    () -> underTest.updateAnimeUsers(user)
            )
                    .isInstanceOf(AnimeUserException.class)
                    .hasMessageContaining("User is not registered");
        }

        @Test
        @DisplayName("Testing if method calls findById in animeRepository")
        void testingIfItCallsFindMethodOfRepository() throws AnimeUserException {
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(false);

            //when
            underTest.updateAnimeUsers(user);

            //then
            verify(animeUserRepository).findById(anyString());
        }

        @Test
        @DisplayName("Testing if method updates the user after finding it")
        void testingIfItUpdatesOnFindingTheUser() throws AnimeUserException {
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(false);

            //when
            underTest.updateAnimeUsers(user);

            //then
            verify(animeUserRepository).save(any());
        }
    }

    @Nested
    @DisplayName("While testing getUserAndAnimes method")
    class testingGetUserAndAnimes{
        @Test
        @DisplayName("Testing if method throws exception if the user is not registered")
        void testingIfItThrowsException(){
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(true);

            //when
            //then
            assertThatThrownBy(
                    () -> underTest.updateAnimeUsers(user)
            )
                    .isInstanceOf(AnimeUserException.class)
                    .hasMessageContaining("User is not registered");
        }
        @Test
        @DisplayName("Testing if method calls findById in animeRepository")
        void testingIfItCallsFindMethodOfRepository() throws AnimeUserException {
            //given
            String userEmail = "user";
            AnimeUser user = AnimeUser.builder()
                    .email(userEmail)
                    .build();

            given(underTest.checkIfEmailDoesNotExists(anyString())).willReturn(false);

            //when
            underTest.updateAnimeUsers(user);

            //then
            verify(animeUserRepository).findById(anyString());
        }
    }


}