package com.za5groszy.foundation.market.domain.event;

import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.sharedkernel.event.AggregateEventEmitter;
import com.za5groszy.foundation.sharedkernel.event.EventEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketEventEmitter extends AggregateEventEmitter {
    @Autowired
    public MarketEventEmitter(MarketRepository repository, EventEmitter emitter) {
        super(repository, emitter);
    }
}
