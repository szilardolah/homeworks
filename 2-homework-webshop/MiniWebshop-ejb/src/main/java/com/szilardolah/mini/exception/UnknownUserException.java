package com.szilardolah.mini.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownUserException extends RuntimeException {

    public UnknownUserException() {
        super();
    }
   
    public UnknownUserException(String message) {
        super(message);
    }

}
