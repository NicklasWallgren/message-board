package dev.nicklasw.messageboard;

import java.util.Collections;

import dev.nicklasw.messageboard.domain.user.entities.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomWithSecurityContextFactory implements WithSecurityContextFactory<WithStatelessUser> {

    @Override
    public SecurityContext createSecurityContext(final WithStatelessUser annotation) {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();

        final User principal = User.of(annotation.userId(), annotation.username(), annotation.password());

        final Authentication authentication = new UsernamePasswordAuthenticationToken(
            principal, annotation.password(), Collections.emptyList());

        context.setAuthentication(authentication);

        return context;
    }

}
