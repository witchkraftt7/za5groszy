package com.za5groszy.foundation.market.domain;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;

public interface MarketRepository extends AggregateRepository {
    int getUserBids(UserId userId);

    boolean isItemAuctionInProgress(ItemId itemId);
}
