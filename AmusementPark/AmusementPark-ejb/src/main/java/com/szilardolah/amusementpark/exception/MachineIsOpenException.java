package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class MachineIsOpenException extends RuntimeException {

    public MachineIsOpenException() {
        super();
    }

    public MachineIsOpenException(String message) {
        super(message);
    }

}
