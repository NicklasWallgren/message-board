package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ClientException {

    private NotFoundException(final String missingObjectName, final List<Pair<String, Object>> byCriteria) {
        super(HttpStatus.NOT_FOUND, message(missingObjectName, byCriteria));
    }

    private static String message(final String missingObjectName, final List<Pair<String, Object>> byCriteria) {
        return missingObjectString(missingObjectName) + " - " + criteriaString(byCriteria);
    }

    private static String missingObjectString(final String missingObjectName) {
        return org.springframework.util.StringUtils.capitalize(missingObjectName.toLowerCase());
    }

    private static String criteriaString(final List<Pair<String, Object>> byCriteria) {
        return byCriteria.stream()
            .map(p -> p.getKey() + "=" + p.getValue())
            .collect(Collectors.joining(", "));
    }

    /**
     * TODO.
     *
     * @param missingObjectName todo
     * @param criteriaKey1      todo
     * @param criteriaValue1    todo
     * @return todo
     */
    public static NotFoundException of(@NonNull final String missingObjectName, @NonNull final String criteriaKey1,
                                       @NonNull final Object criteriaValue1) {
        return new NotFoundException(missingObjectName,
            Collections.singletonList(Pair.of(criteriaKey1, criteriaValue1)));
    }

}
