package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.exceptions.UnknownUserException;
import com.szilardolah.webshop.szilard.olah.exceptions.UserAlreadyExistsException;
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
        } else {
            throw new UserAlreadyExistsException("User already exists" +
                    "username: " + userDTO.getUsername() +
                    "password: " + userDTO.getPassword());
        }
        return userDTO;
    }
    
    public UserDTO getUser(String username) {
        UserDTO returnValue = users.get(username);
        if (returnValue != null) {
            return users.get(username);
        } else {
            throw new UnknownUserException(
                    "Unknown user by given username. First, add to database.");
        }
    }    
    
    public boolean authenticate(String username, String password) {
        if (hasUser(username)) {
            UserDTO user = users.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }
    
    private boolean hasUser(String username) {
        return users.containsKey(username);
    }
}
