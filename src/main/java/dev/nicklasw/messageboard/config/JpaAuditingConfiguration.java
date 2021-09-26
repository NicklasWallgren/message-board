package dev.nicklasw.messageboard.config;

import java.util.Optional;

import dev.nicklasw.messageboard.adapter.driven.security.AuthenticationService;
import dev.nicklasw.messageboard.domain.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(modifyOnCreate = false, auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    private final AuthenticationService authenticationService;

    @Bean("auditorProvider")
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(getUsernameFromAuthenticationOrFallback());
    }

    private String getUsernameFromAuthenticationOrFallback() {
        return authenticationService.optionalAuthenticatedUser()
            .map(User::getUsername)
            .orElseGet(() -> "system");
    }

}
