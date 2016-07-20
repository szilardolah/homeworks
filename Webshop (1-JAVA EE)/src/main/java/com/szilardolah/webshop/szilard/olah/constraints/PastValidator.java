package com.szilardolah.webshop.szilard.olah.constraints;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class PastValidator implements ConstraintValidator<Past, Date> {

    @Override
    public void initialize(Past a) {
        //..
    }

    @Override
    public boolean isValid(Date d, ConstraintValidatorContext cvc) {
        if (d == null) {
            return false;
        }      
        return Util.timeInMillis(d) < Util.systemTimeInMillis();
    }

}
