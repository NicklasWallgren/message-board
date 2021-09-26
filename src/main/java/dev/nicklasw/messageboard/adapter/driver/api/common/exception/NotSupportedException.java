package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

public class NotSupportedException extends ClientException {

    public NotSupportedException(@NotNull final String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
