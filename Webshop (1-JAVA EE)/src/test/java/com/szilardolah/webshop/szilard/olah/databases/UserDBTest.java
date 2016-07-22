package com.szilardolah.webshop.szilard.olah.databases;

import com.szilardolah.webshop.szilard.olah.beans.UserDTO;
import com.szilardolah.webshop.szilard.olah.enums.Sex;
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
    public void testRegistrate() {
        userDB.registrate(userDTO);
        assertEquals(1, userDB.members());
    }
    
    @Test
    public void usernameAndPasswordAlreadyExists() {
        userDB.registrate(userDTO);
        userDB.registrate(userDTO);
        assertEquals(1, userDB.members());
    }

    @Test
    public void testGetUser() {
        userDB.registrate(userDTO);
        assertEquals(userDTO, userDB.getUser(userDTO.getUsername()));
    }
    
    @Test
    public void testGetUserReturnNull() {
        assertEquals(null, userDB.getUser(userDTO.getUsername()));
    }

    @Test
    public void testAuthenticate() {
        String username = "www";
        String password = "zzz";
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDB.registrate(userDTO);
        assertEquals(true, userDB.authenticate(username, password));
    }
    
    @Test
    public void testAuthenticateReturnFalse() {
        String username = "xxx";
        String password = "yyy";
        userDB.registrate(userDTO);
        assertEquals(false, userDB.authenticate(username, password));
    }

    @Test
    public void testHasUser() {
        userDB.registrate(userDTO);
        assertEquals(true, userDB.hasUser(userDTO.getUsername())); 
    }
    
        @Test
    public void testHasUserReturnFalse() {
        assertEquals(false, userDB.hasUser(userDTO.getUsername())); 
    }
    
}
