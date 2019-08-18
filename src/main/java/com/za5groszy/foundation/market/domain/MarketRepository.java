package com.za5groszy.foundation.market.domain;

import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;

public interface MarketRepository extends AggregateRepository {
    public int getUserBids(UserId userId);

    public boolean isItemAuctionInProgress(ItemId itemId);
}
