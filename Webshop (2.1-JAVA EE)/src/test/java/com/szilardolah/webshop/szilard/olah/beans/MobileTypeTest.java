package com.szilardolah.webshop.szilard.olah.beans;

import com.szilardolah.webshop.bean.MobileType;
import com.szilardolah.webshop.util.Util;
import com.szilardolah.webshop.enums.Color;
import com.szilardolah.webshop.enums.Currency;
import com.szilardolah.webshop.enums.Manufacturer;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Szilard
 */
public class MobileTypeTest {
    private static ValidatorFactory vf;
    private static Validator validator;
    private MobileType mobileType;
    
    
    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }
    
    @AfterClass
    public static void close() {
        vf.close();
    }
    
    @Before
    public void initMobileType() {
        mobileType = new MobileType(
                 Util.generateUuidInString(),
                "Galaxy s4",
                Manufacturer.SAMSUNG,
                70000,
                Currency.HUF,
                Color.WHITE);
    }

    @Test
    public void optimalCase() {
        checkViolations(0, null);
    }
    
    @Test
    public void uuidHasIncorrectLength() {
        String invalidUuid = "123";
        mobileType.setId(invalidUuid);
        checkViolations(1, invalidUuid);
    }

    @Test
    public void uuidIsNull() {
        mobileType.setId(null);
        checkViolations(1, null);
    }
   
    @Test
    public void typeIsNull() {
        mobileType.setType(null);
        checkViolations(1, null);
    }
    
    @Test
    public void typeHasIncorrectLength() {
        final String invalidType = "ww";  
        mobileType.setType(invalidType);
        checkViolations(1, invalidType);
    }
    
    @Test
    public void manufacturerIsNull() {
        mobileType.setId(null);
        checkViolations(1, null);       
    }

    @Test
    public void priceIsNull() {
        mobileType.setPrice(null);
        checkViolations(1, null);
    }

    @Test
    public void priceHasIncorrectLength() {
        final Integer invalidPrice = 0;
        mobileType.setPrice(invalidPrice);
        checkViolations(1, invalidPrice);
    }
    
    @Test
    public void currencyIsNull() {
        mobileType.setType(null);
        checkViolations(1, null);
    }
    
    @Test
    public void colorIsNull() {
        mobileType.setType(null);
        checkViolations(1, null);
    }
    
           
    @Test
    public void appleHasWhiteColor() {
        Color invalidColor = Color.WHITE;
        mobileType.setManufacturer(Manufacturer.APPLE);
        mobileType.setColor(invalidColor);       
        checkViolations(0, null);
    }
    
    @Test
    public void appleHasBlackColor() {
        Color invalidColor = Color.WHITE;
        mobileType.setManufacturer(Manufacturer.APPLE);
        mobileType.setColor(invalidColor);       
        checkViolations(0, null);
    }
    
    @Test
    public void appleHasNotCorrectColor() {
        Color invalidColor = Color.GREEN;
        mobileType.setManufacturer(Manufacturer.APPLE);
        mobileType.setColor(invalidColor);       
        checkViolations(1, mobileType); 
    }

    @Test
    public void samsungHasNotCorrectColor() {
        Color invalidColor = Color.GREEN;
        mobileType.setManufacturer(Manufacturer.SAMSUNG);
        mobileType.setColor(invalidColor);       
        checkViolations(1, mobileType); 
    }
    
    private void checkViolation(Set<ConstraintViolation<MobileType>> violations, Object invalidObject) {
        ConstraintViolation<MobileType> violation = violations.iterator().next();
        assertEquals(invalidObject, violation.getInvalidValue());
    }
    
    private void checkViolations(int requiredFails, Object invalidObject) {
        Set<ConstraintViolation<MobileType>> violations = validator.validate(mobileType);
        assertEquals(requiredFails, violations.size());
        
        if (violations.size() > 0) {
            checkViolation(violations, invalidObject);
        }
    }
    
}
