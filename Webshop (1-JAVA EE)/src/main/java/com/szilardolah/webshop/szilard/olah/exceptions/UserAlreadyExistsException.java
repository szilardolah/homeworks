package com.szilardolah.webshop.szilard.olah.exceptions;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
