package com.za5groszy.foundation.sharedkernel.event;

import com.za5groszy.foundation.sharedkernel.UserId;

public abstract class AggregateEvent {
    private UserId userId;

    public AggregateEvent(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public AggregateEvent clone() {
        try {
            return (AggregateEvent) super.clone();
        } catch (CloneNotSupportedException exception) {
            // cloning is supported
            return this;
        }
    }
}
