package com.szilardolah.webshop.constraint;

import com.szilardolah.webshop.bean.MobileType;
import com.szilardolah.webshop.enums.Color;
import static com.szilardolah.webshop.enums.Color.*;
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
    public boolean isValid(MobileType mobileType, ConstraintValidatorContext cvc) {
        Color mobileColor = mobileType.getColor();
        
        switch (mobileType.getManufacturer()) {
            case APPLE:
                if (mobileColor != WHITE  &&  mobileColor != BLACK) {
                    return false;
                }
                break;
            case SAMSUNG:
                if (mobileColor == GREEN) {
                    return false;
                }
                break;
            default:
                return true;
        }
        
        return true;            
    }

}
