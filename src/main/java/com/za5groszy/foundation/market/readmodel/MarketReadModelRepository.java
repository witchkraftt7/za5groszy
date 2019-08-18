package com.za5groszy.foundation.market.readmodel;

import com.za5groszy.foundation.market.sharedkernel.item.Item;

import java.util.List;

public interface MarketReadModelRepository {
    List<Item> getCurrentState();
}
