package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.beans.MobileType;
import com.szilardolah.webshop.szilard.olah.enums.Color;
import com.szilardolah.webshop.szilard.olah.enums.Currency;
import com.szilardolah.webshop.szilard.olah.enums.Manufacturer;
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
    public void testAddNewMobileType() {      
        inventory.addNewMobileType(mobileType);
        assertEquals(true, mobileType.getId().matches("^.+"));
        assertEquals(0, inventory.getQuantity(mobileType));
    }
    
    
    @Test
    public void MobileTypeAlreadyAdded() {      
        inventory.addNewMobileType(mobileType);        
        assertEquals(null, inventory.addNewMobileType(mobileType));
        assertEquals(1, inventory.numOfMubileType());
    }
    

    @Test
    public void testReserveMobile() {
        inventory.addNewMobileType(mobileType);
        assertEquals(true, inventory.increaseQuantity(mobileType, 20));
        assertEquals(true, inventory.reserveMobile(mobileType, 10));
        assertEquals(10, inventory.getQuantity(mobileType));
    }
    
    @Test
    public void testReserveMobileReturnFalse() {
        inventory.addNewMobileType(mobileType);
        assertEquals(true, inventory.increaseQuantity(mobileType, 10));
        assertEquals(false, inventory.reserveMobile(mobileType, 20));
        assertEquals(10, inventory.getQuantity(mobileType));
    }

    @Test
    public void testReturnMobile() {  
        inventory.addNewMobileType(mobileType);
        assertEquals(true, inventory.returnMobile(mobileType, 20));
        assertEquals(20, inventory.getQuantity(mobileType));
    }
    
    @Test
    public void testReturnMobileReturnFalse() {  
        inventory.addNewMobileType(mobileType);
        mobileType.setColor(Color.BLUE);
        assertEquals(false, inventory.returnMobile(mobileType, 20));
    }
    
    @Test
    public void testHasMobileType() {  
        inventory.addNewMobileType(mobileType);
        assertEquals(true, inventory.hasMobileType(mobileType.getType(), 
                mobileType.getManufacturer(), mobileType.getColor()));
    }
   
    @Test
    public void testHasMobileTypeReturnFalse() {  
        inventory.addNewMobileType(mobileType);
        assertEquals(false, inventory.hasMobileType(mobileType.getType(), 
                mobileType.getManufacturer(), Color.BLUE));
    }   
}
