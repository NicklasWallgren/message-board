package dev.nicklasw.messageboard.adapter.driver.api.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerError extends ServerError {

    public InternalServerError(final String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerError(final String message, final Throwable throwable) {
        super(INTERNAL_SERVER_ERROR, message, throwable);
    }
}
