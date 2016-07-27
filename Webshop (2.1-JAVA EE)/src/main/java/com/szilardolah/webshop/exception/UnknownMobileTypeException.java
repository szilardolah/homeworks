package com.szilardolah.webshop.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownMobileTypeException extends RuntimeException {

    public UnknownMobileTypeException() {
        super();
    }

    public UnknownMobileTypeException(String message) {
        super(message);
    }
}
