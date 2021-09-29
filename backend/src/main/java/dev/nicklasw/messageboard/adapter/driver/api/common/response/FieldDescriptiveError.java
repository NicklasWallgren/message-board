package dev.nicklasw.messageboard.adapter.driver.api.common.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FieldDescriptiveError implements Serializable {

    private final String field;
    private final String description;
    @Nullable

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object rejectedValue;

    public static FieldDescriptiveError of(final String field, final String description) {
        return new FieldDescriptiveError(field, description, null);
    }

    public static FieldDescriptiveError of(final String field, final String description, final Object rejectedValue) {
        return new FieldDescriptiveError(field, description, rejectedValue);
    }

}
