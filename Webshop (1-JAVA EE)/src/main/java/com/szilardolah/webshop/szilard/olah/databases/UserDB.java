package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.beans.UserDTO;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UserDB {
    
    private final Map<String, UserDTO> users;
    
    public UserDB() {
        this.users = new HashMap<>();
    }
    
    public UserDTO registrate(UserDTO userDTO) {
        if (!authenticate(userDTO.getUsername(), userDTO.getPassword())) {
            userDTO.setRegistrationDate(Calendar.getInstance().getTime());
            users.put(userDTO.getUsername(), userDTO);
        }
        return userDTO;
    }
    
    public UserDTO getUser(String username) {
        return users.get(username);
    }    
    
    public boolean authenticate(String username, String password) {
        if (hasUser(username)) {
            UserDTO user = users.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }
    
    public boolean hasUser(String username) {
        return users.containsKey(username);
    }
         
    public int members() {
        return users.size();
    }
}
