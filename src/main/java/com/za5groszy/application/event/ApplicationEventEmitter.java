package com.za5groszy.application.event;

import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.event.EventEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventEmitter implements EventEmitter {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void emit(AggregateEvent event) {
        publisher.publishEvent(event);
    }
}
