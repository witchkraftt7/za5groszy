package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.Auction;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.UserBidBalance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.mockito.Mockito.*;

public class MarketDbRepositoryTest {
    private Session sessionMock;
    private MarketDbRepository sut;

    @Test
    public void shouldPersistUserBidAction() {
        UserId userId = new UserId(22);
        ItemId itemId = new ItemId(1);
        UserBadeUp event = new UserBadeUp(
                userId,
                itemId
        );

        UserBidBalance bidBalanceMock = Mockito.mock(UserBidBalance.class);
        Auction auctionMock = Mockito.mock(Auction.class);
        when(sessionMock.get(UserBidBalance.class, event.getUserId().getId())).thenReturn(bidBalanceMock);
        when(bidBalanceMock.getBidBalance()).thenReturn(5);
        when(sessionMock.get(Auction.class, event.getItemId().getId())).thenReturn(auctionMock);
        Date now = new Date();
        Date nowSecondLater = new Date(now.getTime() + 1);
        when(auctionMock.getFinishedAt()).thenReturn(now);

        UserBadeUp persistedEvent = (UserBadeUp) sut.persist(event);

        verify(bidBalanceMock).setBidBalance(4);
        verify(auctionMock).setFinishedAt(ArgumentMatchers.refEq(nowSecondLater));
        verify(sessionMock).get(UserBidBalance.class, event.getUserId().getId());
        verify(sessionMock).get(Auction.class, event.getItemId().getId());
        verify(sessionMock).save(auctionMock);
        verify(sessionMock).save(bidBalanceMock);
    }

    @Before
    public void setUp() {
        SessionFactory sessionFactoryMock = Mockito.mock(SessionFactory.class);
        sessionMock = Mockito.mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        sut = new MarketDbRepository(sessionFactoryMock);
    }
}
