package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class GuestIsAlreadyInTheParkException extends RuntimeException {

    public GuestIsAlreadyInTheParkException() {
        super();
    }

    public GuestIsAlreadyInTheParkException(String message) {
        super(message);
    }

}
