package com.za5groszy.foundation.sharedkernel.event;

import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;

public abstract class AggregateEventEmitter {
    private EventEmitter emitter;

    private AggregateRepository repository;

    public AggregateEventEmitter(AggregateRepository repository, EventEmitter emitter) {
        this.repository = repository;
        this.emitter = emitter;
    }

    public AggregateEvent emit(AggregateEvent event) {
        AggregateEvent persistedEvent = repository.persist(event);
        emitter.emit(persistedEvent);

        return persistedEvent;
    }
}

