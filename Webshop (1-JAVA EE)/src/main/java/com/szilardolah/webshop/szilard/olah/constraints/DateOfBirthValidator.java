package com.szilardolah.webshop.szilard.olah.constraints;

import com.szilardolah.webshop.szilard.olah.beans.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, UserDTO> {

    @Override
    public void initialize(DateOfBirth a) {
        //..
    }

    @Override
    public boolean isValid(UserDTO t, ConstraintValidatorContext cvc) {
        if (t == null) {
            return false;
        }
        if (t.getDateOfBirth() == null || t.getRegistrationDate() == null) {
            return false;
        }
        
        if (Util.timeInMillis(t.getDateOfBirth()) < Util.timeInMillis(t.getRegistrationDate()) &&
            Util.differenceInYear(t.getDateOfBirth()) >= 18) {
                return true;
        }
        
        return false;
    }

}
