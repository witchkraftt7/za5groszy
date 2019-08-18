package com.za5groszy.foundation.sharedkernel.event;

public interface EventEmitter {
    public void emit(AggregateEvent event);
}
