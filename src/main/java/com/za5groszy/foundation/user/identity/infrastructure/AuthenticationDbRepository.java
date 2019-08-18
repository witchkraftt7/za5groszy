package com.za5groszy.foundation.user.identity.infrastructure;

import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.user.identity.domain.AuthenticationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationDbRepository implements AuthenticationRepository {
    @Override
    public AggregateEvent persist(AggregateEvent event) {
        return event;
    }
}
