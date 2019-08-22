package com.za5groszy.foundation.market.domain;

import com.za5groszy.foundation.market.domain.event.MarketEventEmitter;
import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.domain.event.UserBoughtItem;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Market {
    private MarketRepository repository;
    private MarketEventEmitter emitter;

    @Autowired
    public Market(MarketRepository repository, MarketEventEmitter emitter) {
        this.repository = repository;
        this.emitter = emitter;
    }

    public UserBadeUp bidUp(UserId userId, ItemId itemId) throws InsufficientAmountOfBidsException, ItemAuctionFinishedException {
        if (repository.getUserBids(userId) <= 0) {
            throw new InsufficientAmountOfBidsException(userId, itemId);
        }

        if (!repository.isItemAuctionInProgress(itemId)) {
            throw new ItemAuctionFinishedException(userId, itemId);
        }

        return (UserBadeUp) emitter.emit(
                new UserBadeUp(
                        userId,
                        itemId
                )
        );
    }

    public UserBoughtItem buyNow(UserId userId, ItemId itemId) throws ItemAuctionFinishedException {
        if (!repository.isItemAuctionInProgress(itemId)) {
            throw new ItemAuctionFinishedException(userId, itemId);
        }

        return (UserBoughtItem) emitter.emit(
                new UserBoughtItem(
                        userId,
                        itemId
                )
        );
    }
}
