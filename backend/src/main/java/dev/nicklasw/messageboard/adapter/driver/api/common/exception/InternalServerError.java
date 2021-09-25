package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.NonNull;

public class InternalServerError extends ServerError {

    public InternalServerError(@NonNull final String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }

}
