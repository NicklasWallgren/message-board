package dev.nicklasw.messageboard.adapter.driver.api.security.responses;

import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiResponse;
import lombok.Value;

@Value(staticConstructor = "of")
public class AuthenticationResponse implements ApiResponse {
    Long id;
    String username;
    String token;
}
