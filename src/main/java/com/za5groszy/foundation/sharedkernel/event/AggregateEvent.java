package com.za5groszy.foundation.sharedkernel.event;

import com.za5groszy.foundation.sharedkernel.UserId;

public abstract class AggregateEvent {
    private UserId userId;
    private Object source;

    public AggregateEvent(Object source, UserId userId) {
        this.source = source;
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Object getSource() {
        return source;
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
