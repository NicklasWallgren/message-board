package dev.nicklasw.messageboard.adapter.driven.security;

import java.util.Optional;

import dev.nicklasw.messageboard.config.WebSecurityConfiguration;
import dev.nicklasw.messageboard.domain.user.entities.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {
    /**
     * Authenticates a {@link User} by username and password.
     * <p>
     * Using {@link DaoAuthenticationProvider} as the default {@link AuthenticationProvider}
     * See configuration {@link WebSecurityConfiguration}
     *
     * @param username to be authenticated by
     * @param password to be authenticated by
     * @return the {@link Authentication}
     * @throws AuthenticationException  if authentication fails
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     * @throws IllegalArgumentException if {@literal password} is {@literal null}.
     */
    Authentication authenticate(final String username, final String password);

    /**
     * Authenticates a user by the user id.
     * <p>
     * Using {@link DaoAuthenticationProvider} as the default {@link AuthenticationProvider}
     * See configuration {@link WebSecurityConfiguration}
     *
     * @param userId to be authenticated by
     * @return the {@link Authentication}
     * @throws AuthenticationException  if authentication fails
     * @throws IllegalArgumentException if {@literal userId} is {@literal null}
     */
    Authentication authenticate(final Long userId);

    /**
     * Registers a new {@link User} by username and password.
     * <p>
     * Using {@link DaoAuthenticationProvider} as the default {@link AuthenticationProvider}
     * See configuration {@link WebSecurityConfiguration}
     *
     * @param username to be authenticated by
     * @param password to be authenticated by
     * @return the {@link User}
     * @throws AuthenticationException  if authentication fails
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     * @throws IllegalArgumentException if {@literal password} is {@literal null}
     */
    User register(final String username, final String password);

    /**
     * Returns the authenticated {@link User} or {@literal Optional#empty()} if none found.
     *
     * @return the authenticated {@link User} or {@literal Optional#empty()}
     */
    Optional<User> optionalAuthenticatedUser();

    /**
     * Returns the {@link Authentication} or {@literal Optional#empty()} if none found.
     *
     * @return the {@link Authentication}  or  {@literal Optional#empty()}
     */
    Optional<Authentication> optionalAuthentication();
}
