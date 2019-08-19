package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.models.Auction;
import com.za5groszy.foundation.models.User;
import com.za5groszy.foundation.models.UserBidBalance;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Repository
@Transactional
public class MarketDbRepository implements MarketRepository, AggregateRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public AggregateEvent persist(AggregateEvent event) {
        if (event instanceof UserBadeUp) {
            return onItemBidUp((UserBadeUp) event);
        }

        return event;
    }

    private UserBadeUp onItemBidUp(UserBadeUp event) {
        UserBidBalance bidBalance = sessionFactory.getCurrentSession().get(UserBidBalance.class, event.getUserId().getId());
        bidBalance.setBidBalance(bidBalance.getBidBalance() - 1);
        sessionFactory.getCurrentSession().save(bidBalance);

        Auction auction = sessionFactory.getCurrentSession().get(Auction.class, event.getItemId().getId());
        Date finishesAt = new Date(auction.getFinishesAt().getTime() + 1);
        auction.setFinishesAt(finishesAt);
        sessionFactory.getCurrentSession().save(auction);

        return event.withExpirationDate(Duration.between(Instant.now(), finishesAt.toInstant()));
    }

    @Override
    public int getUserBids(UserId userId) {
        UserBidBalance bidBalance = sessionFactory.getCurrentSession().get(UserBidBalance.class, userId.getId());

        return bidBalance.getBidBalance();
    }

    @Override
    public boolean isItemAuctionInProgress(ItemId itemId) {
        Auction auction = sessionFactory.getCurrentSession().get(Auction.class, itemId.getId());

        return auction.getFinishesAt().after((new Date()));
    }
}
