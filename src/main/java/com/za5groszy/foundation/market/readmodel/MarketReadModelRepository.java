package com.za5groszy.foundation.market.readmodel;

import java.util.List;

public interface MarketReadModelRepository {
    List<Auction> getCurrentState();
}
