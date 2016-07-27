package com.szilardolah.webshop.database;

import com.szilardolah.webshop.exception.UnknownUserException;
import com.szilardolah.webshop.exception.UserAlreadyExistsException;
import com.szilardolah.webshop.bean.UserDTO;
import static com.szilardolah.webshop.database.MobileInventory.getInstance;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
@Singleton
public class UserDB {
    
    private static final Logger LOGGER = Logger.getLogger(MobileInventory.class.getSimpleName());
       
    private final Map<String, UserDTO> users;
    
    private static UserDB instance;

    
    private UserDB() {
        this.users = new HashMap<>();
    }
    
    public UserDTO registrate(@Observes UserDTO user) {
        if (!authenticate(user.getUsername(), user.getPassword())) {
            user.setRegistrationDate(Calendar.getInstance().getTime());
            users.put(user.getUsername(), user);
            LOGGER.log(Level.INFO, "Added useer. username: {0}", new Object[]{user.getUsername()});
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
    
    public static UserDB getInstance(Instance instance) {
        switch(instance) {
            case NEW:
                UserDB.instance = new UserDB();
                return UserDB.instance;
            case EXISTING:
                return (instance == null) ? getInstance(Instance.NEW) : UserDB.instance; 
            default:
                throw new IllegalArgumentException("Wrong parameter.");
        }
    }
}
