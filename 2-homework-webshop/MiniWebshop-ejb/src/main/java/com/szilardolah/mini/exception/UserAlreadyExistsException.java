package com.szilardolah.mini.exception;

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
