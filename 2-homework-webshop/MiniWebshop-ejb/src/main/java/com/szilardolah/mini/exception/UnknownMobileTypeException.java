package com.szilardolah.mini.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownMobileTypeException extends RuntimeException {

    public static final String MESSAGE = " is unknown! First, add to the MobileInventory.";
    public UnknownMobileTypeException() {
        super();
    }

    public UnknownMobileTypeException(String message) {
        super(message + MESSAGE);
    }
}
