package dev.nicklasw.messageboard.adapter.driver.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import dev.nicklasw.messageboard.adapter.driver.api.common.exception.ClientException;
import dev.nicklasw.messageboard.adapter.driver.api.common.response.ApiError;
import dev.nicklasw.messageboard.adapter.driver.api.common.response.BadRequestApiError;
import dev.nicklasw.messageboard.adapter.driver.api.common.response.FieldDescriptiveError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiError> handleMissingEntity(final ClientException exception) {
        return new ApiError(exception.getStatus(), exception.getMessage())
            .toResponseEntity();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(final AuthenticationException exception, final HttpServletResponse response) {
        return new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage()).toResponseEntity();
    }

    @Override
    public ResponseEntity handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final HttpHeaders headers, final HttpStatus status,
                                                       final WebRequest request) {
        final List<FieldDescriptiveError> errors = exception.getBindingResult()
            .getAllErrors()
            .stream()
            .map(ApiExceptionHandler::buildFieldDescriptiveError)
            .toList();

        return new BadRequestApiError(status, "Bad request", errors).toResponseEntity();
    }

    private static FieldDescriptiveError buildFieldDescriptiveError(final ObjectError error) {
        if (error instanceof FieldError fieldError) {
            return FieldDescriptiveError.of(fieldError.getField(), error.getDefaultMessage(), fieldError.getRejectedValue());
        }

        return FieldDescriptiveError.of(error.getObjectName(), error.getDefaultMessage());
    }

}
