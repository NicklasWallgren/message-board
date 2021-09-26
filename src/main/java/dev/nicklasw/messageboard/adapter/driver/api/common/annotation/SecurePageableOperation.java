package dev.nicklasw.messageboard.adapter.driver.api.common.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;

@Target({METHOD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Operation(parameters = {
    @Parameter(in = ParameterIn.QUERY,
        description = "Page you want to retrieve (0..N)",
        name = "page",
        content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
    @Parameter(in = ParameterIn.QUERY,
        description = "Number of records per page.",
        name = "size",
        content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
    @Parameter(in = ParameterIn.QUERY,
        description = "Sorting criteria in the format: property(,asc|desc). "
            + "Default sort order is ascending. " + "Multiple sort criteria are supported.",
        name = "sort",
        allowEmptyValue = true)
})
public @interface SecurePageableOperation {

    @AliasFor(annotation = Operation.class)
    String summary() default "";

    @AliasFor(annotation = Operation.class, value = "security")
    SecurityRequirement[] security() default {@SecurityRequirement(name = "bearerAuth")};

}
