package com.za5groszy.foundation.user.identity.application;

import com.za5groszy.foundation.user.identity.application.command.AuthenticateUser;
import com.za5groszy.foundation.user.identity.domain.UserIdentity;
import com.za5groszy.foundation.user.identity.domain.event.UserAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIdentityService {
    @Autowired
    UserIdentity identity;

    public UserAuthenticated authenticate(AuthenticateUser command) {
        return identity.authenticate(command.getUserId());
    }
}
