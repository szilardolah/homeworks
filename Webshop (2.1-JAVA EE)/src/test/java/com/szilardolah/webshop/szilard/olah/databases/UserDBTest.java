package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.database.UserDB;
import com.szilardolah.webshop.bean.UserDTO;
import com.szilardolah.webshop.enums.Sex;
import com.szilardolah.webshop.exception.UserAlreadyExistsException;
import com.szilardolah.webshop.exception.UnknownUserException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*; 

/**
 *
 * @author Szilard
 */
public class UserDBTest {
    
    private UserDTO userDTO;
    private UserDB userDB;
    
    @Before
    public void init() {
        userDB = new UserDB(); 
        userDTO = new UserDTO.Builder("silverwolf454", "blackjac123AAA..,", "samuel.ross@example.com")
                .setAddress("5655 Oregon, Santa Ana")
                .setPhone("+36-102365478")
                .setFirstname("Samuel")
                .setLastname("Ross")
                .setSex(Sex.MALE)
                .setDateOfBirth(new Date(590968800000l))
                .setAdmin(true)
                .build();
    }

   @Test
    public void registrate() {
        userDB.registrate(userDTO);
        assertTrue(userDB.authenticate(userDTO.getUsername(), userDTO.getPassword()));
    }
    
    @Test (expected = UserAlreadyExistsException.class)
    public void registrateExceptedException() {
        userDB.registrate(userDTO);
        userDB.registrate(userDTO);
    }

    @Test
    public void getUser() {
        userDB.registrate(userDTO);
        assertEquals(userDTO, userDB.getUser(userDTO.getUsername()));
    }
    
    @Test (expected = UnknownUserException.class)
    public void getUserExceptedException() {
        assertEquals(null, userDB.getUser(userDTO.getUsername()));
    }

    @Test
    public void authenticate() {
        String username = "www";
        String password = "zzz";
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDB.registrate(userDTO);
        assertTrue(userDB.authenticate(username, password));
    }
    
    @Test
    public void authenticateReturnFalse() {
        String username = "xxx";
        String password = "yyy";
        userDB.registrate(userDTO);
        assertFalse(userDB.authenticate(username, password));
    }
}
