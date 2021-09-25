package dev.nicklasw.messageboard.adapter.driver.api.message.responses;

import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiResponse;
import lombok.Value;

@Value(staticConstructor = "of")
public class MessageResponse implements ApiResponse {
    Long id;
    String subject;
    String text;
    User user;

    @Value(staticConstructor = "of")
    public static class User {
        Long id;
        String username;
    }

}
