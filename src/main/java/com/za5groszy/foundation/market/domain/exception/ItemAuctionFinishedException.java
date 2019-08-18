package com.za5groszy.foundation.market.domain.exception;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;

public class ItemAuctionFinishedException extends MarketException {
    public ItemAuctionFinishedException(UserId userId, ItemId itemId) {
        super(
                "Item auction finished.",
                userId,
                itemId,
                MarketException.ITEM_AUCTION_FINISHED_ERROR_CODE
        );
    }
}
