package dev.nicklasw.messageboard.adapter.driver.api.security;

import javax.validation.Valid;

import dev.nicklasw.messageboard.adapter.driven.security.AuthenticationService;
import dev.nicklasw.messageboard.adapter.driver.api.common.annotation.ApiController;
import dev.nicklasw.messageboard.adapter.driver.api.common.annotation.ApiPost;
import dev.nicklasw.messageboard.adapter.driver.api.security.converters.AuthenticationApiConverter;
import dev.nicklasw.messageboard.adapter.driver.api.security.requests.AuthenticationRequest;
import dev.nicklasw.messageboard.adapter.driver.api.security.requests.RegisterRequest;
import dev.nicklasw.messageboard.adapter.driver.api.security.responses.AuthenticationResponse;
import dev.nicklasw.messageboard.adapter.driver.api.security.responses.UserRegisterResponse;
import dev.nicklasw.messageboard.domain.user.entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@ApiController("/api/auth")
@Tag(name = "UserAuthentication", description = "Operations concerning user authentication")
public class UserAuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationApiConverter apiConverter;

    @Operation(summary = "Login a user by username and password.")
    @ApiPost("/login")
    public AuthenticationResponse login(@Valid @Parameter(description = "Authentication credentials.")
                                        @RequestBody final AuthenticationRequest request) {
        final Authentication authentication = authenticationService.authenticate(request.getUsername(), request.getPassword());

        return apiConverter.responseOf(authentication);
    }

    @ApiPost("/register")
    @Operation(summary = "Register a new user by username and password.")
    public UserRegisterResponse register(@Valid @Parameter(description = "User credentials.")
                                         @RequestBody final RegisterRequest request) {
        final User user = authenticationService.register(request.getUsername(), request.getPassword());

        return apiConverter.responseOf(user);
    }

}
