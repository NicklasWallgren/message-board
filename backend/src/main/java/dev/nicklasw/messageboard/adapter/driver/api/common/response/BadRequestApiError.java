package dev.nicklasw.messageboard.adapter.driver.api.common.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestApiError extends ApiError {

    @JsonProperty("fieldErrors")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldDescriptiveError> fieldErrors = new ArrayList<>();

    /**
     * BadRequestApiError constructor.
     *
     * @param status  the selected response status
     * @param message the message
     * @throws IllegalArgumentException if {@literal status} is {@literal null}.
     * @throws IllegalArgumentException if {@literal message} is {@literal null}.
     */
    public BadRequestApiError(@NonNull final HttpStatus status, @NonNull final String message) {
        super(status, message);
    }

    /**
     * BadRequestApiError constructor.
     *
     * @param status  the selected response status
     * @param message the message
     * @param fieldErrors the field errors
     * @throws IllegalArgumentException if {@literal status} is {@literal null} .
     * @throws IllegalArgumentException if {@literal message} is {@literal null}.
     * @throws IllegalArgumentException if {@literal fieldErrors} is {@literal null}.
     */
    public BadRequestApiError(@NonNull final HttpStatus status, @NonNull final String message, @NonNull final List<FieldDescriptiveError> fieldErrors) {
        super(status, message);

        // Do not store a mutable object into this instance
        this.fieldErrors = new ArrayList<>(fieldErrors);
    }

}
