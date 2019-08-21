package com.za5groszy.foundation.user.registration.domain.exception;

import com.za5groszy.foundation.sharedkernel.exception.DomainModelException;

public class UserRegistrationException extends DomainModelException {
    protected static final int USER_ALREADY_EXISTS = 20;

    public UserRegistrationException(String message, int errorCode) {
        super(message, errorCode);
    }
}
