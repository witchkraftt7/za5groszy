package com.za5groszy.foundation.market.application;

import com.za5groszy.foundation.market.application.command.UpBid;
import com.za5groszy.foundation.market.domain.Market;
import com.za5groszy.foundation.market.domain.event.ItemBidUp;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
    @Autowired
    private Market market;

    public ItemBidUp upBid(UpBid command) throws InsufficientAmountOfBidsException, ItemAuctionFinishedException {
        return market.upBid(command.getUserId(), command.getItemId());
    }
}