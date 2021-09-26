package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import dev.nicklasw.messageboard.domain.MessageBoardEntity;
import org.springframework.http.HttpStatus;

public final class MissingEntityException extends ClientException {

    private static final String DEFAULT_MESSAGE = "Missing entity";

    /**
     * MissingEntityException constructor.
     */
    public MissingEntityException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, DEFAULT_MESSAGE);
    }

    /**
     * MissingEntityException constructor.
     *
     * @param message (required)
     */
    public MissingEntityException(final String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public static <E extends MessageBoardEntity> MissingEntityException of(final Class<E> entityClass, final Long id) {
        return new MissingEntityException("Missing " + entityClass.getSimpleName().toLowerCase() + " with id " + id);
    }

}
