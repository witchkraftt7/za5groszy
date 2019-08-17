package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.models.ItemBid;
import com.za5groszy.foundation.models.UserBidBalance;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Repository
public class MarketDbRepository extends AggregateRepository implements MarketRepository {

    @Override
    public Item upBid(UserId userId, ItemId itemId) {
        getCurrentSession().beginTransaction();

        UserBidBalance bidBalance = getCurrentSession()
                .byNaturalId(UserBidBalance.class)
                .using("user_id", userId.getId())
                .load();
        bidBalance.setBidBalance(bidBalance.getBidBalance() - 1);

        getCurrentSession().save(bidBalance);

        ItemBid itemBid = getCurrentSession()
                .byNaturalId(ItemBid.class)
                .using("item_id", itemId.getId())
                .load();
        itemBid.setUserId(userId.getId());
        itemBid.setAuctionEndsOn(addSecond(itemBid.getAuctionEndsOn()));

        getCurrentSession().save(itemBid);

        getCurrentSession().getTransaction().commit();

        return new Item(userId, itemId, Duration.between(Instant.now(), itemBid.getAuctionEndsOn().toInstant()));
    }

    private Date addSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, 1);

        return cal.getTime();
    }

    @Override
    public int getUserBids(UserId userId) {
        UserBidBalance bidBalance = getCurrentSession()
                .byNaturalId(UserBidBalance.class)
                .using("user_id", userId.getId())
                .load();

        return bidBalance.getBidBalance();
    }
}
