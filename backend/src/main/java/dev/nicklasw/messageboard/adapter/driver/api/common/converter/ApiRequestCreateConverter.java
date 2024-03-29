package dev.nicklasw.messageboard.adapter.driver.api.common.converter;

import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiRequest;
import dev.nicklasw.messageboard.domain.MessageBoardEntity;

public interface ApiRequestCreateConverter<E extends MessageBoardEntity, R extends ApiRequest> {
    /**
     * Converts a {@link R} into a {@link E}.
     *
     * @param request to be converted
     * @return a {@link E}
     * @throws IllegalArgumentException if {@literal request} is {@literal null}.
     */
    E toDomain(final R request);
}
