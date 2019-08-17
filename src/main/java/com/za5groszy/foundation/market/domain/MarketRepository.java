package com.za5groszy.foundation.market.domain;

import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;

public interface MarketRepository {
    public Item upBid(UserId userId, ItemId itemId);

    public int getUserBids(UserId userId);
}
