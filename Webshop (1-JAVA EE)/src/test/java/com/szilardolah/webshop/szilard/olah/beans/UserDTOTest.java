/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.szilardolah.webshop.szilard.olah.beans;

import enums.Sex;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Szilard
 */
public class UserDTOTest {
    private static ValidatorFactory vf;
    private static Validator validator;
    private UserDTO userDTO;
    
    
    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }
    
    @AfterClass
    public static void close() {
        vf.close();
    }
    
    @Before
    public void initUserDTO() {
        userDTO = new UserDTO.Builder("silverwolf454", "blackjac123AAA..,", "samuel.ross@example.com")
                .setFirstname("Samuel")
                .setLastname("Ross")
                .setAddress("5655 Oregon, Santa Ana")
                .setPhone("+36-102365478")
                .setSex(Sex.MALE)
                .setDateOfBirth(new Date(590968800000l))
                .setAdmin(true)
                .build();
    }

    @Test
    public void optimalCase() {
       checkViolations(0, null);
    }

    @Test
    public void usernameIsNull() {
        userDTO.setUsername(null);
        checkViolations(1, null);
    }

    @Test
    public void usernameHasIncorrectLength() {
        String incorrectUsername = "yyy";
        userDTO.setUsername(incorrectUsername);
        checkViolations(1, incorrectUsername);
    }

    @Test
    public void addressIsIncorrect() {
        String incorrectAddress = "yyy";
        userDTO.setAddress(incorrectAddress);
        checkViolations(1, incorrectAddress);
    }

    @Test
    public void phoneNumStartsWithIncorrectParameter() {
        String incorrectPhone = "+90-999999999";
        userDTO.setPhone(incorrectPhone);
        checkViolations(1, incorrectPhone);
    }

    @Test
    public void phoneNumHasIncorrectLength() {
        String incorrectPhone = "+90-333";
        userDTO.setPhone(incorrectPhone);
        checkViolations(1, incorrectPhone);
    }

    @Test
    public void emailIsNull() {
        String incorrectEmail = null;
        userDTO.setEmail(incorrectEmail);
        checkViolations(1, incorrectEmail);
    }

    @Test
    public void emailHasIncorrectSyntax() {
        String incorrectEmail = "123@foo.2";
        userDTO.setEmail(incorrectEmail);
        checkViolations(1, incorrectEmail);
    }

    @Test
    public void registrationDateIsNull() {
        Date incorrectRegDate = null;
        userDTO.setRegistrationDate(incorrectRegDate);
        checkViolations(2, incorrectRegDate);
    }

    @Test
    public void registrationDateIsIncorrect() {
        Date incorrectRegDate = new Date(1506117600000l);
        userDTO.setRegistrationDate(incorrectRegDate);
        checkViolations(1, incorrectRegDate);
    }

    @Test
    public void testGetPhone() {
    }

    @Test
    public void testSetPhone() {
    }

    @Test
    public void testGetEmail() {
    }

    @Test
    public void testSetEmail() {
    }

    @Test
    public void testGetSex() {
    }

    @Test
    public void testSetSex() {
    }

    @Test
    public void testGetRegistrationDate() {
    }

    @Test
    public void testSetRegistrationDate() {
    }

    @Test
    public void testGetDateOfBirth() {
    }

    @Test
    public void testSetDateOfBirth() {
    }

    @Test
    public void testIsAdmin() {
    }

    @Test
    public void testSetAdmin() {
    }
  
    
    private void checkViolation(Set<ConstraintViolation<UserDTO>> violations, Object invalidObejct) {
        ConstraintViolation<UserDTO> violation = violations.iterator().next();
        assertEquals(invalidObejct, violation.getInvalidValue());
    }
    
    private void checkViolations(int requiredFails, Object invalidObject) {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
        assertEquals(requiredFails, violations.size());
        
        if (violations.size() > 0) {
            checkViolation(violations, invalidObject);
        }
    }
}
