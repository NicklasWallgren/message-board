package dev.nicklasw.messageboard.adapter.driven.security;

import static java.util.Collections.emptyList;

import java.util.Collections;
import java.util.Optional;

import dev.nicklasw.messageboard.adapter.driver.api.common.exception.RegisterException;
import dev.nicklasw.messageboard.domain.user.entities.User;
import dev.nicklasw.messageboard.domain.user.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public Authentication authenticate(@NonNull final String username, @NonNull final String password) {
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password, emptyList()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @Override
    public Authentication authenticate(@NonNull final Long userId) {
        final User user = userService.findById(userId)
            .orElseThrow(() -> new BadCredentialsException("Unable to authenticate user with id " + userId));

        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @Override
    public User register(@NonNull final String username, @NonNull final String password) {
        userService.findByUsername(username)
            .ifPresent(it -> {
                throw new RegisterException("User already exists with that username.");
            });

        return userService.create(User.of(username, password));
    }

    @Override
    public Optional<User> optionalAuthenticatedUser() {
        return optionalAuthentication()
            .filter(Authentication::isAuthenticated)
            .filter(it -> it.getPrincipal() instanceof User)
            .map(it -> (User) it.getPrincipal());
    }

    @Override
    public Optional<Authentication> optionalAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

}
