package com.szilardolah.webshop.szilard.olah.constraints;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */

@Constraint(validatedBy = PastValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface Past {
    String message() default "{past.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
