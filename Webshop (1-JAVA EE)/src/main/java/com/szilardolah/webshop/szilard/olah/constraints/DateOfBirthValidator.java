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
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext cvc) {
        if (userDTO == null || userDTO.getRegistrationDate() == null || userDTO.getDateOfBirth() == null) {
            return true;
        }  
        
        long dateOfBirthInMillis = userDTO.getDateOfBirth().getTime();
        long registrationDateInMillis = userDTO.getRegistrationDate().getTime();
        
        return  dateOfBirthInMillis < registrationDateInMillis;
    }    
}
