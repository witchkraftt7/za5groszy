package com.za5groszy.foundation.market.application;

import com.za5groszy.foundation.market.application.command.UpBid;
import com.za5groszy.foundation.market.domain.Market;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
    @Autowired
    private Market market;

    public Item upBid(UpBid command) throws InsufficientAmountOfBidsException, ItemAuctionFinishedException {
        return market.upBid(command.getUserId(), command.getItemId());
    }
}
