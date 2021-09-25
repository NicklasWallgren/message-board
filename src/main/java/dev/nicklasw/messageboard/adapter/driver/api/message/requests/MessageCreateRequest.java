package dev.nicklasw.messageboard.adapter.driver.api.message.requests;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.nicklasw.messageboard.adapter.driver.api.response.ApiRequest;
import lombok.Getter;

@Getter
public class MessageCreateRequest implements ApiRequest {

    @NotBlank
    private final String text;

    @JsonCreator
    public MessageCreateRequest(
        @JsonProperty(value = "text", required = true) final String text
    ) {
        this.text = text;
    }

}
