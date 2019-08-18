package com.za5groszy.foundation.user.identity.application.command;

import com.za5groszy.foundation.sharedkernel.UserId;

public class AuthenticateUser {
    private UserId userId;

    public AuthenticateUser(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }
}
