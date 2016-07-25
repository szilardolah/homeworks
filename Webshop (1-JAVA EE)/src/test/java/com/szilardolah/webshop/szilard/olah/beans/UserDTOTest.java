package com.szilardolah.webshop.szilard.olah.beans;

import com.szilardolah.webshop.szilard.olah.enums.Sex;
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
        userDTO = new UserDTO.Builder("silverwolf454", ".<blackjacK>.", "samuel.ross@example.com")
                .setAddress("5655 Oregon, Santa Ana")
                .setPhone("+36102365478")
                .setFirstname("Samuel")
                .setLastname("Ross")
                .setSex(Sex.MALE)
                .setDateOfBirth(new Date(590968800000l))
                .setAdmin(true)
                .build();
    }

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
        final String incorrectUsername = "yyy"; 
        userDTO.setUsername(incorrectUsername);
        checkViolations(1, incorrectUsername);
    }

    @Test
    public void passwordIsNull() {
        userDTO.setPassword(null); 
        checkViolations(1, null);
    }
    
    @Test
    public void passwordHasIncorrectLength() { 
        final String invalidPassword = "aa"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(1, invalidPassword);
    }
    
    @Test
    public void passwordContainsLowerCaseCharactersOnly() {
        final String invalidPassword = "aaabbcc"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(1, invalidPassword);
    }
    
    @Test
    public void passwordContainsUpperCaseCharactersOnly() { 
        final String invalidPassword = "AABBCC"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(1, invalidPassword);
    }
    
    @Test
    public void passwordContainsDigitsOnly() { 
        final String invalidPassword = "123"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(1, invalidPassword);
    }
 
    @Test
    public void passwordContainsSpecialCharactersOnly() {
        final String invalidPassword = "=+<>.,"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(1, invalidPassword);
    }
    
    @Test
    public void passwordContainsLowerAndUpperCaseCharacters() {
        final String invalidPassword = "AABBCCxxxyyyzzz"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(1, invalidPassword);
    }
    
    @Test
    public void passwordContainsLowerCaseAndUpperCaseCharactersAndDigits() { 
        final String invalidPassword = "AABBCCxxxyyyzzz33"; 
        userDTO.setPassword(invalidPassword);
        checkViolations(0, invalidPassword);
    }
    
    @Test
    public void passwordContainsLowerCaseAndUpperCaseAndSpecialCharacters() {
        final String invalidPassword = "AABBCCxxxyyyzzz./="; 
        userDTO.setPassword(invalidPassword);
        checkViolations(0, invalidPassword);
    }
    
    @Test
    public void addressHasIncorrectSyntax() {
        final String incorrectAddress = "yyyy kkk";
        userDTO.setAddress(incorrectAddress);
        checkViolations(1, incorrectAddress);
    }
    
    @Test
    public void phoneNumberHasIncorrectSyntax() {
        final String incorrectPhone = "+90-4444";
        userDTO.setPhone(incorrectPhone);
        checkViolations(1, incorrectPhone);
    }
    
    @Test
    public void emailIsNull() {
        userDTO.setEmail(null);
        checkViolations(1, null);
    }
    
    @Test
    public void emailHasIncorrectSyntax() {
        final String invalidEmail = "foo@example.h";
        userDTO.setEmail(invalidEmail);
        checkViolations(1, invalidEmail);
    }
    
    @Test
    public void registrationDateIsIncorrect() {
        final Date incorrectRegDate = new Date(1506117600000l);
        userDTO.setRegistrationDate(incorrectRegDate);
        checkViolations(1, incorrectRegDate);
    }
    
    @Test
    public void givenBirthDateIsIncorrect() {
        userDTO.setDateOfBirth(new Date(1506117600000l)); 
        checkViolations(1, userDTO);
    }
    
    @Test
    public void firstNameAddedOnly() {
        userDTO.setFirstname("Samuel");
        userDTO.setLastname(null);
        checkViolations(1, userDTO);
    }
    
    @Test
    public void lastNameAddedOnly() {
        userDTO.setFirstname(null);
        userDTO.setLastname("Ross");
        checkViolations(1, userDTO);
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
