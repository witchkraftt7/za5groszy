package com.za5groszy.foundation.user.identity.domain.event;

import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;

public class UserAuthenticated extends AggregateEvent {
    public UserAuthenticated(Object source, UserId userId) {
        super(source, userId);
    }
}
