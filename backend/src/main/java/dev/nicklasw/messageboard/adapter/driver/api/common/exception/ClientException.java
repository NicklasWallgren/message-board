package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ClientException extends RuntimeException {
    private final HttpStatus status;

    protected ClientException(@NonNull final HttpStatus status, @NonNull final String message) {
        super(message);
        this.status = status;
        this.validateSelf();
    }

    private void validateSelf() {
        if (!this.status.is4xxClientError()) {
            throw new InternalServerError("Do not create ClientException that not should be translated as a 4NN status, got " + status);
        }
    }
}
