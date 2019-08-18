package com.za5groszy.foundation.market.application.command;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;

public class UpBid {
    private UserId userId;
    private ItemId itemId;

    public UpBid(UserId userId, ItemId itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public UserId getUserId() {
        return userId;
    }

    public ItemId getItemId() {
        return itemId;
    }
}
