package com.szilardolah.webshop.szilard.olah.main;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.szilardolah.webshop.szilard.olah.beans.MobileType;
import com.szilardolah.webshop.szilard.olah.beans.UserDTO;
import com.szilardolah.webshop.szilard.olah.databases.MobileInventory;
import com.szilardolah.webshop.szilard.olah.databases.UserDB;
import java.util.logging.Level;
import java.util.logging.Logger;


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
     
    public MobileInventory setMobileInventory(String filename) {
        try {
            MobileType[] mobileTypes = readMobileTypesFromJson(filename);
            
            MobileInventory inventory = new MobileInventory();
            for(MobileType m : mobileTypes) {
                inventory.addNewMobileType(m);
            }
            return inventory;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private MobileType[] readMobileTypesFromJson(String filename) throws IOException {
        return objMapper.readValue(
                    Resources.getResource(filename),
                    MobileType[].class);
    }
    
    public UserDB setUserDB(String filename) {
        try {
            UserDTO[] userDTOs = readUserDTOsFromJson(filename);
            UserDB userDB = new UserDB();
            for(UserDTO u : userDTOs) {
                userDB.registrate(u);
            }
            return userDB;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }
   
    private UserDTO[] readUserDTOsFromJson(String filename) throws IOException {
        return objMapper.readValue(
                    Resources.getResource(filename),
                    UserDTO[].class); 
    } 
    
    public static void main(String[] args) throws IOException {        
        Main m = new Main();
        MobileInventory inventory = m.setMobileInventory("mobiletypes.json");
        UserDB userDB = m.setUserDB("users.json");
        LOGGER.log(Level.INFO, "Number of known phones: {0}", inventory.knownMobiles());
        LOGGER.log(Level.INFO, "Registered users: {0}", userDB.members());
    }
}
