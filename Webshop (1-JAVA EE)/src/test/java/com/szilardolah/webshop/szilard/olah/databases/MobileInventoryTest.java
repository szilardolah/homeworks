package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.beans.MobileType;
import com.szilardolah.webshop.szilard.olah.enums.Color;
import com.szilardolah.webshop.szilard.olah.enums.Currency;
import com.szilardolah.webshop.szilard.olah.enums.Manufacturer;
import com.szilardolah.webshop.szilard.olah.exceptions.MobileTypeAlreadyExistsException;
import com.szilardolah.webshop.szilard.olah.exceptions.UnknownMobileTypeException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Szilard
 */
public class MobileInventoryTest {
    
    private MobileType mobileType;
    private MobileInventory inventory;
    
    @Before
    public void init() {
        inventory = new MobileInventory();
        mobileType = new MobileType(
                "Galaxy s4",
                Manufacturer.SAMSUNG,
                70000,
                Currency.HUF,
                Color.WHITE);
    }

    @Test
    public void addNewMobileType() {      
        mobileType = inventory.addNewMobileType(mobileType);
        assertTrue(mobileType.getId().matches("^.+"));
        assertTrue(inventory.returnMobile(mobileType, 10));
    }
       
    @Test (expected = MobileTypeAlreadyExistsException.class)
    public void addNewMobileTypeExceptedException() {      
         inventory.addNewMobileType(mobileType);        
        inventory.addNewMobileType(mobileType);
    }
    
    @Test
    public void reserveMobile() {
        inventory.addNewMobileType(mobileType);
        inventory.returnMobile(mobileType, 20);
        assertTrue(inventory.reserveMobile(mobileType, 10));
    }
    
    @Test
    public void reserveMobileReturnFalse() {
        inventory.addNewMobileType(mobileType);
        inventory.returnMobile(mobileType, 10);
        assertFalse(inventory.reserveMobile(mobileType, 20));
    }
    
    @Test (expected = UnknownMobileTypeException.class)
    public void reserveMobileExceptedException() {
        inventory.returnMobile(mobileType, 10);
    }

    @Test
    public void testReturnMobile() {  
         inventory.addNewMobileType(mobileType);
        assertTrue(inventory.returnMobile(mobileType, 20));
    }

    @Test (expected = UnknownMobileTypeException.class)
    public void testReturnMobileExceptedException() {  
        inventory.returnMobile(mobileType, 20);
    }
}
