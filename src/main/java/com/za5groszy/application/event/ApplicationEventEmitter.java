package com.za5groszy.application.event;

import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.event.EventEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
final public class ApplicationEventEmitter implements EventEmitter {
    private ApplicationEventPublisher publisher;

    @Autowired
    public ApplicationEventEmitter(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void emit(AggregateEvent event) {
        publisher.publishEvent(event);
    }
}
