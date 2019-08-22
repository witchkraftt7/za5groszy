package com.za5groszy.foundation.market.application;

import com.za5groszy.foundation.market.application.command.BidUp;
import com.za5groszy.foundation.market.domain.Market;
import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
    private Market market;

    @Autowired
    public MarketService(Market market) {
        this.market = market;
    }

    public UserBadeUp bidUp(BidUp command) throws InsufficientAmountOfBidsException, ItemAuctionFinishedException {
        return market.bidUp(command.getUserId(), command.getItemId());
    }
}
