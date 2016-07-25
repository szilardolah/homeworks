package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.exceptions.UnknownMobileTypeException;
import com.szilardolah.webshop.szilard.olah.beans.MobileType;
import com.szilardolah.webshop.szilard.olah.utils.Util;
import com.szilardolah.webshop.szilard.olah.exceptions.MobileTypeAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MobileInventory {
        
    private final Map<MobileType, Integer> mobiles;
    
    public MobileInventory() {
        this.mobiles = new HashMap<>();
    }
    
    public MobileType addNewMobileType(MobileType mobileType) {
       if (!hasMobileType(mobileType)) {
            mobileType.setId(Util.generateUuidInString());
            mobiles.put(mobileType, 0);
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
}
