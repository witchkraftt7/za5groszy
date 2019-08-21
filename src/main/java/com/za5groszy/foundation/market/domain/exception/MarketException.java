package com.za5groszy.foundation.market.domain.exception;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.exception.DomainModelException;

public class MarketException extends DomainModelException {
    protected static final int INSUFFICIENT_AMOUNT_OF_BIDS_ERROR_CODE = 10;
    protected static final int ITEM_AUCTION_FINISHED_ERROR_CODE = 11;

    private ItemId itemId;
    private UserId userId;

    protected MarketException(String message, UserId userId, ItemId itemId, int errorCode) {
        super(message, errorCode);
        this.itemId = itemId;
        this.userId = userId;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public UserId getUserId() {
        return userId;
    }
}
