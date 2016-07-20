package com.szilardolah.webshop.szilard.olah.constraints;

import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
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
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, ElementType.TYPE})
@Retention(RUNTIME)
public @interface Past {
    String message() default "{past.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
