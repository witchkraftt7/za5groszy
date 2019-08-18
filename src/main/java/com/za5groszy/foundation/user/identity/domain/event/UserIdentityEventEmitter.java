package com.za5groszy.foundation.user.identity.domain.event;

import com.za5groszy.foundation.sharedkernel.event.AggregateEventEmitter;
import com.za5groszy.foundation.sharedkernel.event.EventEmitter;
import com.za5groszy.foundation.user.identity.domain.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserIdentityEventEmitter extends AggregateEventEmitter {
    @Autowired
    AuthenticationRepository repository;

    @Autowired
    EventEmitter emitter;

    public UserIdentityEventEmitter(AuthenticationRepository repository, EventEmitter emitter) {
        super(repository, emitter);
    }
}
