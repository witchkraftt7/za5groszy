package com.za5groszy.foundation.user.registration.domain.exception;

import com.za5groszy.foundation.sharedkernel.Email;

public class UserAlreadyExistsException extends UserRegistrationException {
    private Email email;

    public UserAlreadyExistsException(Email email) {
        super("User already exists.", USER_ALREADY_EXISTS);
        this.email = email;
    }

    public Email getEmail() {
        return email;
    }
}
