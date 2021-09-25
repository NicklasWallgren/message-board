package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import java.util.UUID;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ServerError extends RuntimeException {
    private final UUID id;
    private final HttpStatus status;

    protected ServerError(final HttpStatus status, final String message) {
        super(message(status, message));
        this.id = UUID.randomUUID();
        this.status = status;
        this.validateSelf();
    }

    private static String message(final HttpStatus status, final String message) {
        return status.getReasonPhrase() + ": " + message;
    }

    private void validateSelf() {
        if (!this.status.is5xxServerError()) {
            throw new InternalServerError("Do not create ClientException that not should be translated as a 5NN status, got " + status);
        }
    }
}
