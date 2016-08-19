package com.szilardolah.mini.constraint;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Constraint( validatedBy = MobileColorValidator.class )
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface MobileColor {
    String message() default "{mobilecolor.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
