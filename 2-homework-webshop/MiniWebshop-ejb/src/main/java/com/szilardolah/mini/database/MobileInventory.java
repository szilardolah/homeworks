package com.szilardolah.mini.database;

import com.szilardolah.mini.exception.UnknownMobileTypeException;
import com.szilardolah.mini.bean.MobileType;
import com.szilardolah.mini.util.Util;
import com.szilardolah.mini.exception.MobileTypeAlreadyExistsException;
import com.szilardolah.mini.interceptor.Validation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.ejb.Singleton;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Singleton
@Validation
public class MobileInventory implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(MobileInventory.class.getSimpleName());

    private final Map<MobileType, Integer> mobiles;
       
    public MobileInventory() {
        mobiles = new HashMap<>();
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
            throw new UnknownMobileTypeException(mobileType.getType());
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
            throw new UnknownMobileTypeException(mobileType.getType());
        }
        return increaseQuantity(mobileType, quantity);
    }
    
    public MobileType deleteMobile(String type) {
        for (Entry<MobileType, Integer> e : mobiles.entrySet()) {
            if (e.getKey().getType().equals(type)) {
                mobiles.remove(e.getKey());
                return e.getKey();
            }
        }
        throw new UnknownMobileTypeException(type);
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
    
    public MobileType getMobile(String uuid) {
        for (Entry<MobileType, Integer> e : mobiles.entrySet()) {
            MobileType currMobile = e.getKey();
            if (currMobile.getId().equals(uuid)) {
                return currMobile;
            }
        }
        throw new UnknownMobileTypeException(uuid); 
    }
  
    public Collection<MobileType> getMobiles() {
        List<MobileType> mobileList = new ArrayList<>();
        mobiles.entrySet().stream().forEach(e -> mobileList.add(e.getKey()));
        return mobileList;
    }
    
    public Integer getQuantity(MobileType mobile) {
        if (hasMobileType(mobile)) {
            mobiles.get(mobile);
        }
        return 0;
    }
    
    public MobileType findMobile(String mobileName) {
        for(Entry<MobileType, Integer> e : mobiles.entrySet()) {
            if (e.getKey().getType().equals(mobileName)) {
                return e.getKey();
            }
        }
       throw new UnknownMobileTypeException(mobileName); 
    }
}
