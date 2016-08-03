package com.szilardolah.webshop.main;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.szilardolah.webshop.bean.Cart;
import com.szilardolah.webshop.bean.MobileType;
import com.szilardolah.webshop.bean.UserDTO;
import com.szilardolah.webshop.database.MobileInventory;
import com.szilardolah.webshop.database.UserDB;
import com.szilardolah.webshop.enums.Color;
import com.szilardolah.webshop.enums.Currency;
import com.szilardolah.webshop.enums.Manufacturer;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;


/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());
    
    private final UserDB userDB;
    
    private final MobileInventory inventory;
    
    private final ObjectMapper objMapper;
    
    
    public Main(MobileInventory inventory, UserDB userDB) {
        objMapper = new ObjectMapper();
        this.inventory = inventory;
        this.userDB = userDB;
    }
     
    public void setMobileInventory(String filename) {
        try {    
            Arrays.asList(readMobileTypesFromJson(filename)).stream().forEach(inventory::addNewMobileType);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    private MobileType[] readMobileTypesFromJson(String filename) throws IOException {
        return objMapper.readValue(
                    Resources.getResource(filename),
                    MobileType[].class);
    }
    
    public void setUserDB(String filename) {
        try {
            Arrays.asList(readUserDTOsFromJson(filename)).stream().forEach(userDB::registrate);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }
   
    private UserDTO[] readUserDTOsFromJson(String filename) throws IOException {
        return objMapper.readValue(
                    Resources.getResource(filename),
                    UserDTO[].class); 
    } 
    
    
    private void setDatabasesFromJSON() {
        setMobileInventory("mobiletypes.json");
        setUserDB("users.json");   
    }
    
    private void checkCart() throws Exception {
        MobileType mobileOne = new MobileType("Galaxy s100",
                Manufacturer.SAMSUNG, 70000, Currency.HUF, Color.WHITE);
        MobileType mobileTwo = new MobileType("Galaxy s3",
                Manufacturer.SAMSUNG, 30000, Currency.HUF, Color.BLACK);
        
        mobileOne = inventory.addNewMobileType(mobileOne);
        mobileTwo = inventory.addNewMobileType(mobileTwo);
       
        inventory.returnMobile(mobileOne, 20);
        inventory.returnMobile(mobileTwo, 30);      
        
        Cart cart= new Cart(inventory);
        cart.addPhone(mobileOne, 10);
        cart.addPhone(mobileTwo, 5);
        cart.clear();
        
        cart.addPhone(mobileOne, 10);
        cart.addPhone(mobileOne, 5);
        cart.addPhone(mobileTwo, 5);
        if (cart.getTotalAmount() != 1200000) {
            throw new Exception(cart.getTotalAmount() +".........");
        }
        cart.deletePhone(mobileTwo, 2);
        cart.deletePhone(mobileTwo, 111);
        if (cart.getTotalAmount() != 1140000) {
            throw new Exception(cart.getTotalAmount() +".........");
        }
        
        cart.orderCart();
    }
    
    public static void main(String[] args) throws Exception { 
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
                
        Main m = new Main(container.instance().select(MobileInventory.class).get(),
                          container.instance().select(UserDB.class).get());
               
        m.setDatabasesFromJSON();   
        m.checkCart();
              
        weld.shutdown();
    }


}
