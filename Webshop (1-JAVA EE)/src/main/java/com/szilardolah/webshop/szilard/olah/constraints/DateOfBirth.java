package com.szilardolah.webshop.szilard.olah.constraints;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@ReportAsSingleViolation
@Constraint(validatedBy = {DateOfBirthValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOfBirth {
        
    String message() default "{dateofbirth.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
