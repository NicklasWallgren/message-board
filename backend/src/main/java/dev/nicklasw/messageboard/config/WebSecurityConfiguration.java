package dev.nicklasw.messageboard.config;

import dev.nicklasw.messageboard.adapter.driver.api.security.AuthenticationExceptionEntryPoint;
import dev.nicklasw.messageboard.adapter.driver.api.security.filters.JwtAuthorizationFilter;
import dev.nicklasw.messageboard.domain.user.service.UserService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationExceptionEntryPoint authenticationExceptionEntryPoint;
    private final UserService userService;

    @Value("${messageboard.security.jwt.secret}")
    private String jwtSecret;
    @Value("${messageboard.security.jwt.expiration-time}")
    private Long jwtExpirationTime;

    @Override
    @SuppressFBWarnings("SPRING_CSRF_PROTECTION_DISABLED")
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
            .headers().frameOptions().disable().and() // H2 console
            .exceptionHandling().authenticationEntryPoint(authenticationExceptionEntryPoint)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), authenticationExceptionEntryPoint, userService, this))
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/health/**").permitAll()
            .antMatchers("/ws/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Only for POC purpose. Use e.g. BCryptPasswordEncoder instead
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}
