package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MachineAlreadyInTheParkException extends RuntimeException {

    public MachineAlreadyInTheParkException() {
        super();
    }
    
    public MachineAlreadyInTheParkException(String message) {
        super(message);
    }

}
