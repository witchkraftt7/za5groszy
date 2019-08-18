package com.za5groszy.foundation.sharedkernel.infrastructure;

import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;

public interface AggregateRepository {
    AggregateEvent persist(AggregateEvent event);
}
