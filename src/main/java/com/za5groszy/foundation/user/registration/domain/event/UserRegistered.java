package com.za5groszy.foundation.user.registration.domain.event;

import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.user.details.domain.UserDetails;

public class UserRegistered extends AggregateEvent {
    private UserDetails details;
    private UserId userId;

    public UserRegistered(UserDetails details) {
        super(details.getUserId());
        this.details = details;
    }

    public UserDetails getDetails() {
        return details;
    }

    public UserRegistered withUserId(UserId userId) {
        UserRegistered event = (UserRegistered) clone();
        event.userId = userId;

        return event;
    }
}
