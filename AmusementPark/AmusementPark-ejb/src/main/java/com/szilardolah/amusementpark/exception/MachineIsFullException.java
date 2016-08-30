package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MachineIsFullException extends RuntimeException {

    public MachineIsFullException() {
        super();
    }

    public MachineIsFullException(String message) {
        super(message);
    }

}
