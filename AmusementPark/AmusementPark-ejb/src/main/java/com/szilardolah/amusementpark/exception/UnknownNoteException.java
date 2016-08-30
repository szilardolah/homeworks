package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownNoteException extends RuntimeException {

    public UnknownNoteException() {
        super();
    }

    public UnknownNoteException(String message) {
        super(message);
    }
    
}
