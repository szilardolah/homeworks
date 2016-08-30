package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownGuestException extends RuntimeException {

    public UnknownGuestException() {
        super();
    }

    public UnknownGuestException(String message) {
        super(message);
    }

}
