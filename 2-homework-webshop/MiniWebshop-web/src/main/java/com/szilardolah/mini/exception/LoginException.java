package com.szilardolah.mini.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class LoginException extends RuntimeException {

    public static final String YOU_ARE_NOT_LOGGED_IN = "You aren't logged in.";
    public static final String HAVE_NOT_ADMIN_PERMISSION = "Admin permission is missing";
    
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    
}
