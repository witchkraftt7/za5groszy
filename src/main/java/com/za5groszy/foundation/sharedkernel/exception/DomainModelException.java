package com.za5groszy.foundation.sharedkernel.exception;

public abstract class DomainModelException extends Exception {
    private int errorCode;

    public DomainModelException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
