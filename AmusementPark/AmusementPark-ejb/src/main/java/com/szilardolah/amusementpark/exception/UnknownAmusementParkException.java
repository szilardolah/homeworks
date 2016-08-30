package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownAmusementParkException extends RuntimeException {

    public UnknownAmusementParkException() {
       super();
    }

    public UnknownAmusementParkException(String message) {
        super(message);
    }
    
}
