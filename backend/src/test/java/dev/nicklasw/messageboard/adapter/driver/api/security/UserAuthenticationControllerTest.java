package dev.nicklasw.messageboard.adapter.driver.api.security;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import dev.nicklasw.messageboard.ApiIntegrationTest;
import dev.nicklasw.messageboard.adapter.driver.api.security.requests.AuthenticationRequest;
import dev.nicklasw.messageboard.adapter.driver.api.security.requests.RegisterRequest;
import dev.nicklasw.messageboard.domain.user.entities.User;
import dev.nicklasw.messageboard.domain.user.entities.UserFactory;
import dev.nicklasw.messageboard.domain.user.entities.UserFaker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class UserAuthenticationControllerTest extends ApiIntegrationTest {

    @Autowired
    private UserFactory userFactory;

    @Test
    void givenUserCredentials_whenLogin_thenIsOk() throws Exception {
        final User statefulUser = userFactory.given(UserFaker.user()
            .withUsername("username")
            .withPassword("password")
            .create());

        final AuthenticationRequest request = new AuthenticationRequest(statefulUser.getUsername(), statefulUser.getPassword());

        whenPost("/api/auth/login", request, HttpStatus.OK)
            .andExpect(jsonPath("$.id", Matchers.isA(Long.class), Long.class))
            .andExpect(jsonPath("$.username", Matchers.equalTo(request.getUsername())))
            .andExpect(jsonPath("$.token", Matchers.isA(String.class)));
    }

    @Test
    void givenInvalidUserCredentials_whenLogin_thenIsAuthorized() throws Exception {
        final AuthenticationRequest request = new AuthenticationRequest("username", "password");

        whenPost("/api/auth/login", request, HttpStatus.UNAUTHORIZED);
    }

    @Test
    void givenUserCredentials_whenRegister_thenIsCreated() throws Exception {
        final RegisterRequest request = new RegisterRequest("username", "password");

        whenPost("/api/auth/register", request, HttpStatus.OK)
            .andExpect(jsonPath("$.id", Matchers.isA(Long.class), Long.class))
            .andExpect(jsonPath("$.username", Matchers.equalTo(request.getUsername())));
    }

    @Test
    void givenUsernameReservedByOtherUser_whenRegister_thenIsBadRequest() throws Exception {
        userFactory.given(UserFaker.user()
            .withUsername("username")
            .withPassword("password")
            .create());

        final RegisterRequest request = new RegisterRequest("username", "password");

        whenPost("/api/auth/register", request, HttpStatus.BAD_REQUEST);
    }

}