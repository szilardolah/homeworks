package com.szilardolah.webshop.szilard.olah.exceptions;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MobileTypeAlreadyExistsException extends RuntimeException{

    public MobileTypeAlreadyExistsException() {
        super();
    }

    public MobileTypeAlreadyExistsException(String message) {
        super(message);
    }
}
