package dev.nicklasw.messageboard.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(modifyOnCreate = false, auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean("auditorProvider")
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable("User");
    }

}
