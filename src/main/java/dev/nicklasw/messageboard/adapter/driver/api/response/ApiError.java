package dev.nicklasw.messageboard.adapter.driver.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class ApiError {

    @NonNull
    protected final HttpStatus status;
    @NonNull
    @JsonProperty("message")
    protected final String message;

    @SuppressWarnings("unchecked")
    public <T extends ApiError> ResponseEntity<T> toResponseEntity() {
        return new ResponseEntity<>((T) this, status);
    }

    @JsonProperty("status")
    protected int getStatusCode() {
        return status.value();
    }
}
