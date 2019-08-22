package com.za5groszy.foundation.market.domain.event;

import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.event.EventEmitter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

// testing constructor
public class MarketEventEmitterTest {
    private MarketEventEmitter sut;

    @Test
    public void shouldEmitEvent() {
        AggregateEvent event = Mockito.mock(AggregateEvent.class);
        sut.emit(event);
    }

    @Before
    public void setUp() {
        sut = new MarketEventEmitter(
                Mockito.mock(MarketRepository.class),
                Mockito.mock(EventEmitter.class)
        );
    }
}
