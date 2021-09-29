package dev.nicklasw.messageboard.adapter.driver.api.security.requests;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiRequest;
import lombok.Getter;

@Getter
public class RegisterRequest implements ApiRequest {

    @NotBlank
    private final String username;
    @NotBlank
    private final String password;

    @JsonCreator
    public RegisterRequest(
        @JsonProperty(value = "username", required = true) final String username,
        @JsonProperty(value = "password", required = true) final String password
    ) {
        this.username = username;
        this.password = password;
    }


}
