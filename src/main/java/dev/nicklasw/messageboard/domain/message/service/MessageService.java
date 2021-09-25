package dev.nicklasw.messageboard.domain.message.service;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.Predicate;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    /**
     * Returns a {@link List} of {@link Message} entities matching the given {@link Predicate}.
     * In case no match could be found, an empty {@link List} is returned.
     *
     * @param predicate must not be {@literal null}.
     * @param pageable  may be {@link Pageable#unpaged()}, must not be {@literal null}.
     * @return a {@link Page} of {@link Message} entities matching the given {@link Predicate}.
     * @throws IllegalArgumentException if {@literal predicate} is {@literal null} or {@literal pageable} is {@literal null}
     */
    Page<Message> findAll(final Predicate predicate, final Pageable pageable);

    /**
     * Retrieves an {@link Message} by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<Message> findById(final Long id);

    /**
     * Retrieves an {@link Message} by its id.
     * Applies row lock in ensure that only one session can modify it at a time.
     * <p>
     * Notice: Can only be called within a transaction scope. Else ...
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    // TODO, exception if no transaction is available
    Optional<Message> findOneForUpdate(final Long id);

    /**
     * Creates an {@link Message}.
     *
     * @param message to be created, must not be {@literal null}.
     * @return the created {@link Message}.
     * @throws IllegalArgumentException if {@literal message} is {@literal null}.
     */
    Message create(final Message message);

    /**
     * Updates an {@link Message}.
     *
     * @param message to be updated, must not be {@literal null}.
     * @return the updated {@link Message}.
     * @throws IllegalArgumentException if {@literal message} is {@literal null}.
     */
    Message update(final Message message);

    /**
     * Deletes a {@link Message}.
     *
     * @param id of the message to be deleted, must not be {@literal null}.
     * @throws IllegalArgumentException if {@literal kd} is {@literal null}.
     */
    Message delete(final Long id);

    /**
     * Deletes a {@link Message}.
     *
     * @param message to be deleted, must not be {@literal null}.
     * @throws IllegalArgumentException if {@literal message} is {@literal null}.
     */
    Message delete(final Message message);

}
