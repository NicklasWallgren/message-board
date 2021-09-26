package dev.nicklasw.messageboard.domain.user.service;

import java.util.Optional;

import dev.nicklasw.messageboard.domain.user.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * Retrieves an {@link User} by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    Optional<User> findById(final Long id);

    /**
     * Retrieves an {@link User} by its username.
     *
     * @param username must not be {@literal null}.
     * @return the entity with the given username or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    Optional<User> findByUsername(final String username);

    /**
     * Creates an {@link User}.
     *
     * @param user to be created, must not be {@literal null}.
     * @return the created {@link User}.
     * @throws IllegalArgumentException if {@literal message} is {@literal null}.
     */
    User create(final User user);

}
