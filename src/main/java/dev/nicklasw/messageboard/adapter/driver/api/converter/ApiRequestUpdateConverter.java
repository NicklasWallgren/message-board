package dev.nicklasw.messageboard.adapter.driver.api.converter;

import dev.nicklasw.messageboard.adapter.driver.api.response.ApiRequest;
import dev.nicklasw.messageboard.domain.MessageBoardEntity;

public interface ApiRequestUpdateConverter<E extends MessageBoardEntity, R extends ApiRequest> {
    /**
     * Converts a {@link R} into a {@link E}.
     *
     * @param request to be converted
     * @return a {@link E}
     * @throws IllegalArgumentException if {@literal request} is {@literal null}.
     */
    E updatedDomain(final Long id, final R request);
}
