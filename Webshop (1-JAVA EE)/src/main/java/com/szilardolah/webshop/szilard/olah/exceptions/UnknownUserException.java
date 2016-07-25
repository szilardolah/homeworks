package com.szilardolah.webshop.szilard.olah.exceptions;

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
