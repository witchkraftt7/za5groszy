package com.za5groszy.foundation.market.application;

import com.za5groszy.foundation.market.application.command.BidUp;
import com.za5groszy.foundation.market.domain.Market;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MarketServiceTest {
    private Market marketMock;
    private MarketService sut;

    @Test
    public void shouldBidUp() throws Exception {
        UserId userId = new UserId(22);
        ItemId itemId = new ItemId(1);

        sut.bidUp(
                new BidUp(
                        userId,
                        itemId
                )
        );

        Mockito.verify(marketMock).bidUp(
                userId,
                itemId
        );
    }

    @Before
    public void setUp() {
        marketMock = Mockito.mock(Market.class);
        sut = new MarketService(marketMock);
    }
}
