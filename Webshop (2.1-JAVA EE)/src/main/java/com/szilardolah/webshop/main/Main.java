package com.szilardolah.webshop.main;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.szilardolah.webshop.bean.Cart;
import com.szilardolah.webshop.bean.MobileType;
import com.szilardolah.webshop.bean.UserDTO;
import com.szilardolah.webshop.database.Instance;
import com.szilardolah.webshop.database.MobileInventory;
import com.szilardolah.webshop.database.UserDB;
import com.szilardolah.webshop.enums.Color;
import com.szilardolah.webshop.enums.Currency;
import com.szilardolah.webshop.enums.Manufacturer;
import com.szilardolah.webshop.enums.Sex;
import com.szilardolah.webshop.service.MobileInventoryService;
import com.szilardolah.webshop.service.UserDBService;
import java.util.Arrays;
import java.util.Date;
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
    private final ObjectMapper objMapper;
    
    public Main() {
        objMapper = new ObjectMapper();
    }
     
    public MobileInventory setMobileInventory(String filename) throws IOException {
            MobileType[] mobileTypes = readMobileTypesFromJson(filename);
            
            MobileInventory inventory = MobileInventory.getInstance(Instance.EXISTING);
            for(MobileType m : mobileTypes) {
                inventory.addNewMobileType(m);
            }
            return inventory;
        
    }
    
    private MobileType[] readMobileTypesFromJson(String filename) throws IOException {
        return objMapper.readValue(
                    Resources.getResource(filename),
                    MobileType[].class);
    }
    
    public UserDB setUserDB(String filename) throws IOException {
            UserDTO[] userDTOs = readUserDTOsFromJson(filename);
            UserDB userDB = UserDB.getInstance(Instance.EXISTING);
            for(UserDTO u : userDTOs) {
                userDB.registrate(u);
            }
            return userDB;
    }
   
    private UserDTO[] readUserDTOsFromJson(String filename) throws IOException {
        return objMapper.readValue(
                    Resources.getResource(filename),
                    UserDTO[].class); 
    } 
    
    public static void main(String[] args) { 
        
        Main m = new Main();
        MobileInventory inventory = MobileInventory.getInstance(Instance.EXISTING);
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        UserDBService userDBService = container.instance().select(UserDBService.class).get();
        MobileInventoryService inventoryService = container.instance().select(MobileInventoryService.class).get();
              
        
        try {
            Arrays.asList(m.readMobileTypesFromJson("mobiletypes.json")).stream().forEach(inventoryService::addMobileType);
            Arrays.asList(m.readUserDTOsFromJson("users.json")).stream().forEach(userDBService::registrateUser);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }  
        
        UserDTO userDTO = new UserDTO.Builder("silverwolf454", ".<blackjacK>.", "samuel.ross@example.com")
                .setAddress("5655 Oregon, Santa Ana")
                .setPhone("+36102365478")
                .setFirstname("Samuel")
                .setLastname("Ross")
                .setSex(Sex.MALE)
                .setDateOfBirth(new Date(590968800000L))
                .setAdmin(true)
                .build();
        userDBService.registrateUser(userDTO);
        
        
        MobileType mobileOne = new MobileType("Galaxy s100",
                Manufacturer.SAMSUNG, 70000, Currency.HUF, Color.WHITE);
        MobileType mobileTwo = new MobileType("Galaxy s3",
                Manufacturer.SAMSUNG, 30000, Currency.HUF, Color.BLACK);
        
        mobileOne = inventoryService.addMobileType(mobileOne);
        mobileTwo = inventoryService.addMobileType(mobileTwo);
       
        inventory.returnMobile(mobileOne, 20);
        inventory.returnMobile(mobileTwo, 30);
        
        Cart cart= new Cart();
        cart.addPhone(mobileOne, 10);
        cart.addPhone(mobileOne, 5);
        cart.addPhone(mobileTwo, 5);
        cart.orderCart();
                
        weld.shutdown();
    }
}
