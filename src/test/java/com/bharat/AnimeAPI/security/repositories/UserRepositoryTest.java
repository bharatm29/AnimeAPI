package com.bharat.AnimeAPI.security.repositories;

import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DisplayName("While testing user repository")
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    @DisplayName("testing save method")
    public void testingSaveMethod(){
        //given
        String email = "bharat@123";
        UserSaveWrapper user = UserSaveWrapper.builder()
                .username("Bharat")
                .password("123")
                .email(email)
                .build();

        //when
        underTest.save(user);

        //then
        assertThat(underTest.existsById(email)).isTrue();
    }

    @Test
    @DisplayName("user does not exist on not saving")
    public void testingSaveMethod_02(){
        //given
        //then
        //when
        assertThat(underTest.existsById("bharat@123")).isFalse();
    }
}