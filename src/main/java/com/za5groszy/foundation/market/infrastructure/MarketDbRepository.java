package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.market.domain.event.ItemBidUp;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Repository
public class MarketDbRepository implements MarketRepository, AggregateRepository {

    @Override
    public AggregateEvent persist(AggregateEvent event) {
        if (event instanceof ItemBidUp) {
            return onItemBidUp((ItemBidUp) event);
        }

        return event;
    }

    private ItemBidUp onItemBidUp(ItemBidUp event) {
        // TODO: implement repository methods
        return event.withExpirationDate(Duration.between(Instant.now(), Instant.now()));
    }

    private Date addSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, 1);

        return cal.getTime();
    }

    @Override
    public int getUserBids(UserId userId) {
        return 5;
    }

    public boolean isItemAuctionInProgress(ItemId itemId) {
        return true;
    }
}
