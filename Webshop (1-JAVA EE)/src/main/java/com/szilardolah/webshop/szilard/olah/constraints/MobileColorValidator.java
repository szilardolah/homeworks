package com.szilardolah.webshop.szilard.olah.constraints;

import com.szilardolah.webshop.szilard.olah.beans.MobileType;
import com.szilardolah.webshop.szilard.olah.enums.Color;
import static com.szilardolah.webshop.szilard.olah.enums.Color.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MobileColorValidator implements ConstraintValidator<MobileColor, MobileType>{


    @Override
    public void initialize(MobileColor a) {
        //..
    }

    @Override
    public boolean isValid(MobileType t, ConstraintValidatorContext cvc) {
        Color mobileColor = t.getColor();
        
        switch (t.getManufacturer()) {
            case APPLE:
                if (mobileColor != WHITE  &&  mobileColor != BLACK)
                    return false;
                break;
            case SAMSUNG:
                if (mobileColor == GREEN)
                    return false;
                break;
            default:
                return true;
        }
        
        return true;            
    }

}
