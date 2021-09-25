package dev.nicklasw.messageboard.adapter.driver.api.message.requests;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiRequest;
import lombok.Getter;

@Getter
public class MessageUpdateRequest implements ApiRequest {

    @NotBlank
    private final String subject;
    @NotBlank
    private final String text;

    @JsonCreator
    public MessageUpdateRequest(
        @JsonProperty(value = "subject", required = true) final String subject,
        @JsonProperty(value = "text", required = true) final String text
    ) {
        this.subject = subject;
        this.text = text;
    }

}
