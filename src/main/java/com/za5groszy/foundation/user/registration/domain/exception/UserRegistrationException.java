package com.za5groszy.foundation.user.registration.domain.exception;

import com.za5groszy.foundation.sharedkernel.exception.DomainModelException;

class UserRegistrationException extends DomainModelException {
    static final int USER_ALREADY_EXISTS = 20;

    UserRegistrationException(String message, int errorCode) {
        super(message, errorCode);
    }
}
