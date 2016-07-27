package com.szilardolah.webshop.database;

import com.szilardolah.webshop.exception.UnknownMobileTypeException;
import com.szilardolah.webshop.bean.MobileType;
import com.szilardolah.webshop.util.Util;
import com.szilardolah.webshop.exception.MobileTypeAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MobileInventory {
    
    private static final Logger LOGGER = Logger.getLogger(MobileInventory.class.getSimpleName());
    
    private final Map<MobileType, Integer> mobiles;
   
    private static MobileInventory instance;
    
    
    private MobileInventory() {
        this.mobiles = new HashMap<>();       
    }
    
    public MobileType addNewMobileType(MobileType mobileType) {
       if (!hasMobileType(mobileType)) {
            mobileType.setId(Util.generateUuidInString());
            mobiles.put(mobileType, 0);
            LOGGER.log(Level.INFO, "Added mobile. UUID: {0} type: {1} manufact.:{2}",
                    new Object[]{mobileType.getId(), mobileType.getType(), mobileType.getManufacturer()});
            return mobileType;
        } else {
            throw new MobileTypeAlreadyExistsException(
                    mobileType.getType() + " is already exists in the inventory!");
       }
    }
    
    public boolean reserveMobile(MobileType mobileType, int quantity) {
        if (!hasMobileType(mobileType)) {
            throw new UnknownMobileTypeException(
                        mobileType.getType() + " is unknown! First, add to the MobileInventory.");
        }
        if (hasEnoughQuantity(mobileType, quantity)) {
            Integer newAmount = mobiles.get(mobileType) - quantity;
            mobiles.replace(mobileType, newAmount);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean returnMobile(MobileType mobileType, int quantity) {
        if (!hasMobileType(mobileType)) {
            throw new UnknownMobileTypeException(
                        mobileType.getType() + " is unknown! First, add to the MobileInventory.");
        }
        return increaseQuantity(mobileType, quantity);
    }
    
    private boolean hasMobileType(MobileType mobileType) {
        return mobiles.containsKey(mobileType);
    }
    
    private boolean hasEnoughQuantity(MobileType mobileType, int quantity) {
        return mobiles.get(mobileType) >= quantity;
    }
    
    private boolean increaseQuantity(MobileType mobileType, Integer quantity) {
        int newQuantity = mobiles.get(mobileType) + quantity;
        mobiles.replace(mobileType, newQuantity);
        return mobiles.get(mobileType) == newQuantity;
    }
    
    
    public static MobileInventory getInstance(Instance instance) {
        switch(instance) {
            case NEW:
                MobileInventory.instance = new MobileInventory();
                return MobileInventory.instance;
            case EXISTING:
                return (instance == null) ? getInstance(Instance.NEW) : MobileInventory.instance; 
            default:
                throw new IllegalArgumentException("Wrong parameter.");
        }       
    }
}
