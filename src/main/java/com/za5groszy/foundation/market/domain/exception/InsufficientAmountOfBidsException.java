package com.za5groszy.foundation.market.domain.exception;

public class InsufficientAmountOfBidsException extends Exception {
    public InsufficientAmountOfBidsException() {
        super("Insufficient amount of bids.");
    }
}
