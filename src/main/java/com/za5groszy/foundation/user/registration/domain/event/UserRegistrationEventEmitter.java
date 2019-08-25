package com.za5groszy.foundation.user.registration.domain.event;

import com.za5groszy.foundation.sharedkernel.event.AggregateEventEmitter;
import com.za5groszy.foundation.sharedkernel.event.EventEmitter;
import com.za5groszy.foundation.user.registration.domain.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
final public class UserRegistrationEventEmitter extends AggregateEventEmitter {
    @Autowired
    public UserRegistrationEventEmitter(UserRegistrationRepository repository, EventEmitter emitter) {
        super(repository, emitter);
    }
}
