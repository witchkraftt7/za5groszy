package com.za5groszy.foundation.market.domain;

import com.za5groszy.foundation.market.domain.event.MarketEventEmitter;
import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class MarketTest {
    private MarketRepository marketRepositoryMock;
    private MarketEventEmitter marketEventEmitterMock;
    private Market sut;

    @Test
    public void shouldBidItem() throws Exception {
        UserId userId = new UserId(22);
        ItemId itemId = new ItemId(2);
        AggregateEvent userBadeUp = new UserBadeUp(userId, itemId);

        when(marketRepositoryMock.isItemAuctionInProgress(itemId)).thenReturn(true);
        when(marketRepositoryMock.getUserBids(userId)).thenReturn(5);

        sut.bidUp(userId, itemId);

        verify(marketRepositoryMock).isItemAuctionInProgress(itemId);
        verify(marketRepositoryMock).getUserBids(userId);
        verify(marketEventEmitterMock).emit(ArgumentMatchers.refEq(userBadeUp));
    }

    @Test(expected = InsufficientAmountOfBidsException.class)
    public void shouldThrowExceptionWHenUserHasNoBids() throws Exception {
        UserId userId = new UserId(22);
        ItemId itemId = new ItemId(2);

        when(marketRepositoryMock.getUserBids(userId)).thenReturn(0);

        sut.bidUp(userId, itemId);

        verify(marketRepositoryMock).getUserBids(userId);
    }

    @Test(expected = ItemAuctionFinishedException.class)
    public void shouldThrowExceptionWhenItemAuctionIsFinished() throws Exception {
        UserId userId = new UserId(22);
        ItemId itemId = new ItemId(2);

        when(marketRepositoryMock.getUserBids(userId)).thenReturn(1);
        when(marketRepositoryMock.isItemAuctionInProgress(itemId)).thenReturn(false);

        sut.bidUp(userId, itemId);

        verify(marketRepositoryMock).getUserBids(userId);
        verify(marketRepositoryMock).isItemAuctionInProgress(itemId);
    }

    @Before
    public void setUp() {
        marketRepositoryMock = Mockito.mock(MarketRepository.class);
        marketEventEmitterMock = Mockito.mock(MarketEventEmitter.class);
        sut = new Market(marketRepositoryMock, marketEventEmitterMock);
    }
}
