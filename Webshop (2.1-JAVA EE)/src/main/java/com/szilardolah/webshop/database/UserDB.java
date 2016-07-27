package com.szilardolah.webshop.database;

import com.szilardolah.webshop.exception.UnknownUserException;
import com.szilardolah.webshop.exception.UserAlreadyExistsException;
import com.szilardolah.webshop.bean.UserDTO;
import com.szilardolah.webshop.interceptor.Validation;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Validation
public class UserDB {
    
    private static final Logger LOGGER = Logger.getLogger(UserDB.class.getSimpleName());
       
    private final Map<String, UserDTO> users;
    
    public UserDB() {
        this.users = new HashMap<>();
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
}
