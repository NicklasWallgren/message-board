package dev.nicklasw.messageboard.adapter.driver.api.common.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends ClientException {

    public AuthenticationException() {
        super(HttpStatus.BAD_REQUEST, "Authentication error");
    }

}
