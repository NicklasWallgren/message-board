package dev.nicklasw.messageboard.adapter.driver.api.security.converters;

import dev.nicklasw.messageboard.adapter.driver.api.security.responses.AuthenticationResponse;
import dev.nicklasw.messageboard.adapter.driver.api.security.responses.UserRegisterResponse;
import dev.nicklasw.messageboard.adapter.driver.api.security.utils.JwtUtils;
import dev.nicklasw.messageboard.config.WebSecurityConfiguration;
import dev.nicklasw.messageboard.domain.user.entities.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationApiConverter {

    private final WebSecurityConfiguration webSecurityConfiguration;

    public AuthenticationResponse responseOf(@NonNull final Authentication authentication) {
        final User user = userOf(authentication);
        final String token = JwtUtils.createToken(user.getUsername(), webSecurityConfiguration.getJwtExpirationTime(), webSecurityConfiguration.getJwtSecret());

        return AuthenticationResponse.of(user.getId(), user.getUsername(), token);
    }

    public UserRegisterResponse responseOf(@NonNull final User user) {
        return UserRegisterResponse.of(user.getId(), user.getUsername());
    }

    private static User userOf(final Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
