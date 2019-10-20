package com.za5groszy.foundation.user.registration.domain.exception;

public class InvalidPasswordStrengthException extends UserRegistrationException {
    private String password;

    public InvalidPasswordStrengthException(String password) {
        super("User already exists.", INVALID_PASSWORD_STRENGTH);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
