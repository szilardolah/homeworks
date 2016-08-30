package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class UnknownMachineException extends RuntimeException {
    
    public UnknownMachineException() {
        super();
    }

    public UnknownMachineException(String message) {
        super(message);
    }
}
