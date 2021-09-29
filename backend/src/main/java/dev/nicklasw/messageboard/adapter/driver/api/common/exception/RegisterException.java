package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class RegisterException extends ClientException {

    public RegisterException(@NonNull final String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
