package com.szilardolah.mini.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szilardolah.mini.bean.MobileType;
import com.szilardolah.mini.bean.UserDTO;
import com.szilardolah.mini.database.MobileInventory;
import com.szilardolah.mini.database.UserDB;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Singleton
@Startup
public class Initialize implements Serializable{

    private static final Logger LOGGER = Logger.getLogger(Initialize.class.getSimpleName());
    
    private final ObjectMapper objMapper = new ObjectMapper();

    @Inject
    private UserDB userDB;
    
    @Inject 
    private MobileInventory inventory;
        
        
    @PostConstruct
    public void run() {             
        setDatabasesFromJSON();   
    }
     
    public void setMobileInventory(String filename) {
        try {    
            Arrays.asList(readMobileTypesFromJson(filename)).stream().forEach(inventory::addNewMobileType);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    private MobileType[] readMobileTypesFromJson(String filename) throws IOException {
        LOGGER.log(Level.INFO, "readMobileTypesFromJson()");   
        return objMapper.readValue(
                    this.getClass().getClassLoader().getResource(filename),
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
        LOGGER.log(Level.INFO, "readUserDTOsFromJson()");   
        return objMapper.readValue(
                    this.getClass().getClassLoader().getResource(filename),
                    UserDTO[].class); 
    } 
    
    
    private void setDatabasesFromJSON() {
        setMobileInventory("json/mobiletypes.json");
        setUserDB("json/users.json");   
    }
}
