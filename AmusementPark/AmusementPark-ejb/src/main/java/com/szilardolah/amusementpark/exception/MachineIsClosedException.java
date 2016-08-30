package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MachineIsClosedException extends RuntimeException {

    public MachineIsClosedException() {
        super();
    }

    public MachineIsClosedException(String message) {
        super(message);
    }

    
}
