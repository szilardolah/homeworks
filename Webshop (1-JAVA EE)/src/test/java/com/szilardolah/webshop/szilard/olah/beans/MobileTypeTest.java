package com.szilardolah.webshop.szilard.olah.beans;

import com.szilardolah.webshop.szilard.olah.constraints.Util;
import com.szilardolah.webshop.szilard.olah.enums.Color;
import com.szilardolah.webshop.szilard.olah.enums.Currency;
import com.szilardolah.webshop.szilard.olah.enums.Manufacturer;
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
    public void uuidHasIncorrectLength() {
        String invalidUuid = "123";
        mobileType.setId(invalidUuid);
        checkViolations(1, invalidUuid);
    }

    @Test
    public void uuidIsNull() {
        String invalidUuid = null;
        mobileType.setId(null);
        checkViolations(1, invalidUuid);
    }

    @Test
    public void manufacturerIsNull() {
        Manufacturer invalidManufacturer = null;
        mobileType.setId(null);
        checkViolations(1, invalidManufacturer);       
    }

    @Test
    public void typeIsNull() {
        String invalidType = null;  
        mobileType.setType(null);
        checkViolations(1, invalidType);
    }

    @Test
    public void priceIsNull() {
        String invalidPrice = null;  
        mobileType.setPrice(null);
        checkViolations(1, invalidPrice);
    }

    @Test
    public void priceHasIncorrectLength() {
        Integer invalidPrice = 0;
        mobileType.setPrice(invalidPrice);
        checkViolations(1, invalidPrice);
    }
    
    @Test
    public void currencyIsNull() {
        Currency invalidCurrency = null;
        mobileType.setType(null);
        checkViolations(1, invalidCurrency);
    }
    
    @Test
    public void colorIsNull() {
        Color invalidColor = null;
        mobileType.setType(null);
        checkViolations(1, invalidColor);
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
