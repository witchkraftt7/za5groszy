package com.za5groszy.foundation.user.registration.application.command;

import com.za5groszy.foundation.user.details.domain.UserDetails;

public class RegisterUser {
    private UserDetails details;

    public RegisterUser(UserDetails details) {
        this.details = details;
    }

    public UserDetails getDetails() {
        return details;
    }
}
