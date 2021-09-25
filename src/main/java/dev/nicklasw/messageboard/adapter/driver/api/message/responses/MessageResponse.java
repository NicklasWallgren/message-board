package dev.nicklasw.messageboard.adapter.driver.api.message.responses;

import dev.nicklasw.messageboard.adapter.driver.api.response.ApiResponse;
import lombok.Value;

@Value(staticConstructor = "of")
public class MessageResponse implements ApiResponse {
    Long id;
    String text;
}
