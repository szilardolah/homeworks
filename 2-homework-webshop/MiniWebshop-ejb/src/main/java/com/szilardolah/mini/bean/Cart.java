package com.szilardolah.mini.bean;

import com.szilardolah.mini.database.MobileInventory;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Stateful
@SessionScoped
public class Cart implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(Cart.class.getSimpleName());
    
    private final Map<MobileType, Integer> myCart;
     
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
        myCart.entrySet().stream().forEach(e -> 
            inventory.returnMobile(e.getKey(), e.getValue())
        );
        myCart.clear();
    }
    
    public int getTotalAmount() {
        int  amount = 0;      
        amount = myCart.entrySet().stream().map(e -> e.getKey().getPrice() * e.getValue()).reduce(amount, Integer::sum);
        return amount;
    }
    
    public void orderCart() {
        LOGGER.log(Level.INFO, "Your ordered phones: ");
        myCart.entrySet().stream().forEach(e -> 
            LOGGER.log(Level.INFO, "{0} ({1}, {2}) - {3} {4} ({5} db)",
                    new Object[]{e.getKey().getType(),
                        e.getKey().getManufacturer(),
                        e.getKey().getColor(),
                        e.getKey().getPrice(), 
                        e.getKey().getCurrency().toString(),
                        e.getValue()})
        );
        LOGGER.log(Level.INFO, "Total amount: {0}", this.getTotalAmount());
        this.clear();
    }
}
