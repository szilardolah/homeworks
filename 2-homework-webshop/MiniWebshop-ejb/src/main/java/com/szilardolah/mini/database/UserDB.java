package com.szilardolah.mini.database;

import com.szilardolah.mini.exception.UnknownUserException;
import com.szilardolah.mini.exception.UserAlreadyExistsException;
import com.szilardolah.mini.bean.UserDTO;
import com.szilardolah.mini.interceptor.Validation;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Singleton
@Validation
public class UserDB implements Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(UserDB.class.getSimpleName());
       
    private final Map<String, UserDTO> users;
    
    public UserDB() {
        users = new HashMap<>();
    }
    
    public UserDTO registrate(UserDTO user) {
        if (!authenticate(user.getUsername(), user.getPassword())) {
            user.setRegistrationDate(Calendar.getInstance().getTime());
            users.put(user.getUsername(), user);
            LOGGER.log(Level.INFO, "Added user. username: {0}", new Object[]{user.getUsername()});
        } else {
            throw new UserAlreadyExistsException("User already exists" +
                    "username: " + user.getUsername() +
                    "password: " + user.getPassword());
        }
        return user;
    }
    
    public UserDTO getUser(String username) {
        UserDTO returnValue = users.get(username);
        if (returnValue != null) {
            return users.get(username);
        } else {
            throw new UnknownUserException(
                    "Unknown user by given username. First, add to the database.");
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
    
    public Collection<UserDTO> getUsers() {
        return users.values();
    }
    
    public boolean isAdmin(UserDTO userDTO) {
        return users.get(userDTO.getUsername()).isAdmin();
    }
    
    public UserDTO remove(UserDTO userDTO) {
        return users.remove(userDTO.getUsername());     
    }
    
    public UserDTO remove(String username) {
        return users.remove(username);     
    }
}
