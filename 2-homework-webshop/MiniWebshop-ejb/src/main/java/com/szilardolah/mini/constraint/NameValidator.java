package com.szilardolah.mini.constraint;

import com.szilardolah.mini.bean.UserDTO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class NameValidator implements ConstraintValidator<Name, UserDTO>{

    @Override
    public void initialize(Name a) {
        //..
        
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext cvc) {
        String firstname = userDTO.getFirstname();
        String lastname = userDTO.getLastname();
        
        return (firstname == null && lastname == null)  ||  (firstname != null && lastname != null);
    }

}
