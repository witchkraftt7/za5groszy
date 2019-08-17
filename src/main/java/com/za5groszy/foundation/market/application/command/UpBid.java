package com.za5groszy.foundation.market.application.command;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;

public class UpBid {
    private ItemId itemId;
    private UserId userId;

    public UpBid(ItemId itemId, UserId userId) {
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
