package com.szilardolah.webshop.bean;

import com.szilardolah.webshop.database.MobileInventory;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class Cart {
    
    private static final Logger LOGGER = Logger.getLogger(Cart.class.getSimpleName());
    
    public final Map<MobileType, Integer> myCart;
     
    private final MobileInventory inventory;
    
    
    public Cart() {
        myCart = new HashMap<>();
        inventory = new MobileInventory();
    }
    
    public Cart(MobileInventory mobileInventory) {
        this.myCart = new HashMap<>();
        this.inventory = mobileInventory;
    }
    
    public boolean addPhone(MobileType mobile, int quantity) {
        if(inventory.reserveMobile(mobile, quantity)){
            if (!myCart.containsKey(mobile)) {
                myCart.put(mobile, quantity);
            } else {
                myCart.replace(mobile, myCart.get(mobile) + quantity);
            }
            return true;
        }
        return false;           
    }
    
    public boolean deletePhone(MobileType mobile, int quantity) {
        if(myCart.get(mobile) >= quantity && inventory.returnMobile(mobile, quantity)){
            myCart.replace(mobile, myCart.get(mobile) - quantity);
            if (myCart.get(mobile) == 0) {
                myCart.remove(mobile);
            }
            return true;
        }
        return false;           
    }
    
    public void clear() {
        myCart.entrySet().stream().forEach(mobileEntry -> 
            inventory.returnMobile(mobileEntry.getKey(), mobileEntry.getValue()));
        myCart.clear();            
    }
    
    public int getTotalAmount() {
        int  amount = 0;       
        amount = myCart.entrySet().stream().map(mobileEntry ->      
            mobileEntry.getKey().getPrice() * mobileEntry.getValue()
        ).reduce(amount, Integer::sum);      
        return amount;
    }
    
    public void orderCart() {
        LOGGER.log(Level.INFO, "Your ordered phones: ");
        myCart.entrySet().stream().forEach(mobileEntry -> 
            LOGGER.log(Level.INFO, "{0} ({1}, {2}) - {3} {4} ({5} db)", 
                    new Object[]{mobileEntry.getKey().getType(), 
                        mobileEntry.getKey().getManufacturer(), 
                        mobileEntry.getKey().getColor(), 
                        mobileEntry.getKey().getPrice(), 
                        mobileEntry.getKey().getCurrency().toString(),
                        mobileEntry.getValue()})
        );
        LOGGER.log(Level.INFO, "Total amount: {0}", this.getTotalAmount());
        this.clear();
    }
}
