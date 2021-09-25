package dev.nicklasw.messageboard.adapter.driver.api.exception;

import org.springframework.http.HttpStatus;

public class BadRequestClientException extends ClientException {

    public BadRequestClientException(final String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public static BadRequestClientException missingEntity() {
        return new BadRequestClientException("Missing entity");
    }

}
