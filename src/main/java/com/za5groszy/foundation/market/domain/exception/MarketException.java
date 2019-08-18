package com.za5groszy.foundation.market.domain.exception;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;

public class MarketException extends Exception {
    protected static final int INSUFFICIENT_AMOUNT_OF_BIDS_ERROR_CODE = 10;
    protected static final int ITEM_AUCTION_FINISHED_ERROR_CODE = 11;

    private ItemId itemId;
    private UserId userId;
    private int errorCode;

    protected MarketException(String message, UserId userId, ItemId itemId, int errorCode) {
        super(message);
        this.itemId = itemId;
        this.userId = userId;
        this.errorCode = errorCode;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public UserId getUserId() {
        return userId;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
