package com.za5groszy.foundation.user.registration.domain;

import com.za5groszy.foundation.user.registration.domain.exception.InvalidPasswordStrengthException;

public class Password {
    private static final String PASSWORD_REGEXP
            = "/^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$/";

    private String password;

    public Password(String password) throws InvalidPasswordStrengthException {
        if (isValid(password)) {
            this.password = password;
        } else {
            throw new InvalidPasswordStrengthException(password);
        }
    }

    @Override
    public String toString() {
        return this.password;
    }

    private boolean isValid(String password) {
        return password.matches(PASSWORD_REGEXP);
    }
}
