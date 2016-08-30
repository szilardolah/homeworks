package com.szilardolah.amusementpark.exception;

/**
 *
 * @author Szilard <szilard.olah@yahoo.com>
 */
public class NoEnoughMoneyException extends RuntimeException {
   
    public NoEnoughMoneyException() {
        super();
    }

    public NoEnoughMoneyException(String message) {
        super(message);
    }

}
