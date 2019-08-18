package com.za5groszy.foundation.sharedkernel.event;

public interface EventEmitter {
    void emit(AggregateEvent event);
}
