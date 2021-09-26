package dev.nicklasw.messageboard.adapter.driver.api.common.converter;

import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiResponse;
import dev.nicklasw.messageboard.domain.MessageBoardEntity;

public interface ApiResponseConverter<E extends MessageBoardEntity, R extends ApiResponse> {

    /**
     * Converts a {@link E} into a {@link R}.
     *
     * @param messageBoardEntity entity to be converted
     * @return a {@link R}
     * @throws IllegalArgumentException if {@literal messageBoardEntity} is {@literal null}.
     */
    R responseOf(final E messageBoardEntity);

}
