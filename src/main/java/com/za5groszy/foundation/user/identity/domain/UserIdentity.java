package com.za5groszy.foundation.user.identity.domain;

import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.user.identity.domain.event.UserAuthenticated;
import com.za5groszy.foundation.user.identity.domain.event.UserIdentityEventEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserIdentity {
    @Autowired
    private UserIdentityEventEmitter emitter;

    public UserAuthenticated authenticate(UserId userId) {
        return (UserAuthenticated) emitter.emit(
                new UserAuthenticated(this, userId)
        );
    }
}
