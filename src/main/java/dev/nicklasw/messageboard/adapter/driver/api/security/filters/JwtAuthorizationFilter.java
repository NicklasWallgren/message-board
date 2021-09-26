package dev.nicklasw.messageboard.adapter.driver.api.security.filters;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.nicklasw.messageboard.adapter.driver.api.security.utils.JwtUtils;
import dev.nicklasw.messageboard.config.WebSecurityConfiguration;
import dev.nicklasw.messageboard.domain.user.entities.User;
import dev.nicklasw.messageboard.domain.user.service.UserService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    private final UserService userService;
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Spring Boot class, should be mutable")
    private final WebSecurityConfiguration webSecurityConfiguration;

    public JwtAuthorizationFilter(final AuthenticationManager authenticationManager,
                                  final AuthenticationEntryPoint authenticationEntryPoint,
                                  final UserService userService,
                                  final WebSecurityConfiguration webSecurityConfiguration) {
        super(authenticationManager, authenticationEntryPoint);

        this.userService = userService;
        this.webSecurityConfiguration = webSecurityConfiguration;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
        throws IOException, ServletException {
        final String authorizationHeader = request.getHeader(HEADER_STRING);

        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authentication;

        try {
            authentication = getAuthentication(authorizationHeader);
        } catch (final JwtException e) {
            getAuthenticationEntryPoint().commence(request, response, new InsufficientAuthenticationException(e.getMessage(), e));

            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    @SuppressWarnings("DefaultCharset")
    private UsernamePasswordAuthenticationToken getAuthentication(final String authorizationHeader) {
        final Claims claims = JwtUtils.parse(authorizationHeader.replace(TOKEN_PREFIX, Strings.EMPTY), webSecurityConfiguration.getJwtSecret());

        final User user = userService.findByUsername(claims.getSubject())
            .orElseThrow(() -> new UsernameNotFoundException("User not found. " + claims.getSubject()));

        return new UsernamePasswordAuthenticationToken(user, Strings.EMPTY, Collections.emptyList());
    }

}
