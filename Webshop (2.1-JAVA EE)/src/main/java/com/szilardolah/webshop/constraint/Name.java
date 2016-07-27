package com.szilardolah.webshop.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */

@Constraint( validatedBy = NameValidator.class )
@Retention (RUNTIME)
@Target(ElementType.TYPE)
public @interface Name {
    String message() default "{name.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
