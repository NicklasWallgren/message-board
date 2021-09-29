package dev.nicklasw.messageboard.adapter.driver.api.security.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtils {

    /**
     * Creates an JWT with subject and a specified expiration time.
     *
     * @param subject        the JWT subject, must not be {@literal null}.
     * @param expirationTime the expiration time in miliseconds, must not be {@literal null}.
     * @return the JWT as a string
     * @throws IllegalArgumentException if {@literal subject} is {@literal null}.
     * @throws IllegalArgumentException if {@literal expirationTime} is {@literal null}.
     * @throws IllegalArgumentException if {@literal secret} is {@literal null}.
     */
    @SuppressWarnings({"DefaultCharset", "JdkObsolete", "JavaUtilDate"})
    public static String createToken(@NonNull final String subject, @NonNull final Long expirationTime, @NonNull final String secret) {
        return Jwts.builder()
            .setClaims(Jwts.claims().setSubject(subject))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .compact();
    }

    /**
     * Parses a JWT token and returns the {@link Claims}.
     *
     * @param jwtToken the provided token, must not be {@literal null}.
     * @param secret   the jwt secret, must not be {@literal null}.
     * @throws IllegalArgumentException if {@literal jwtToken} is {@literal null}.
     * @throws IllegalArgumentException if {@literal secret} is {@literal null}.
     */
    @SuppressWarnings("DefaultCharset")
    public static Claims parse(final String jwtToken, final String secret) {
        final JwtParser parser = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
            .build();

        return parser
            .parseClaimsJws(jwtToken)
            .getBody();
    }

}
