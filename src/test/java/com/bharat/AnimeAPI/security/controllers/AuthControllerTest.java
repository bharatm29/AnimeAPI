package com.bharat.AnimeAPI.security.controllers;

import com.bharat.AnimeAPI.security.models.AuthRegisterRequest;
import com.bharat.AnimeAPI.security.models.UserSaveWrapper;
import com.bharat.AnimeAPI.security.services.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    void testingRegisterUserEndpoint() throws Exception {
        //given
        UserSaveWrapper wrapper = UserSaveWrapper.builder()
                .username("user")
                .email("user@123")
                .password("123")
                .build();

        given(authService.registerUser(wrapper)).willReturn(AuthRegisterRequest.builder()
                .message(
                        String.format(
                                "Registration for %s with email: %s successful",
                                wrapper.getUsername(),
                                wrapper)
                )
                .build());

        //when
        //then
        mockMvc
                .perform(servletContext -> {
                    return get("/")
                            .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                            .buildRequest(servletContext);
                })
                .andDo(print())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk());
    }
}