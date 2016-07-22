package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.exceptions.UnknownMobileTypeException;
import com.szilardolah.webshop.szilard.olah.beans.MobileType;
import com.szilardolah.webshop.szilard.olah.constraints.Util;
import com.szilardolah.webshop.szilard.olah.enums.Color;
import com.szilardolah.webshop.szilard.olah.enums.Manufacturer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MobileInventory {
        
    private  Map<MobileType, Integer> mobiles;
    
    public MobileInventory() {
        this.mobiles = new HashMap<>();
    }
    
    public MobileType addNewMobileType(MobileType mobileType) {
       if (!hasMobileType(mobileType.getType(), mobileType.getManufacturer(), mobileType.getColor())) {
            mobileType.setId(Util.generateUuidInString());
            mobiles.put(mobileType, 0);
            return mobileType;
        }
        return null;
    }
    
    public boolean reserveMobile(MobileType mobileType, int quantity) {
        if (hasEnoughQuantity(mobileType, quantity)) {
            Integer newAmount = mobiles.get(mobileType) - quantity;
            mobiles.replace(mobileType, newAmount);
            return true;
        }
        return false;
    }
    
    public boolean returnMobile(MobileType mobileType, int quantity) {
        return increaseQuantity(mobileType, quantity);
    }
    
    public boolean hasMobileType(MobileType mobileType) {
        return mobiles.containsKey(mobileType);
    }
    
    public boolean hasMobileType(String type, Manufacturer manufacturer, Color color) {
        return mobiles.entrySet().stream().map(entry -> entry.getKey()).anyMatch(mobileType -> mobileType.getType().equals(type) && 
                mobileType.getManufacturer().equals(manufacturer) &&
                mobileType.getColor().equals(color));
    }
    
    private boolean hasEnoughQuantity(MobileType mobileType, int quantity) {
        return mobiles.get(mobileType) >= quantity;
    }
    
    public int getQuantity(MobileType mobileType) {
       if (hasMobileType(mobileType)) {
            return mobiles.get(mobileType);
       }
       throw new UnknownMobileTypeException();
    }
    
    public boolean increaseQuantity(MobileType mobileType, Integer quantity) {
        if (hasMobileType(mobileType)) {
            mobiles.replace(mobileType, mobiles.get(mobileType) + quantity);
            return true;
        }
        return false;
    }
    
    public int numOfMubileType() {
        return mobiles.size();
    }
    
    public void setMobileInventory(Map<MobileType, Integer> mobiles) {
        this.mobiles = mobiles;
    }
    
    public int knownMobiles() {
        return mobiles.size();
    }
}
