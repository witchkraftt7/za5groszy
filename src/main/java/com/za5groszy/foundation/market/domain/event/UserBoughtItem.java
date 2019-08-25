package com.za5groszy.foundation.market.domain.event;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;

final public class UserBoughtItem extends AggregateEvent {
    private ItemId itemId;

    public UserBoughtItem(UserId userId, ItemId itemId) {
        super(userId);
        this.itemId = itemId;
    }

    public ItemId getItemId() {
        return itemId;
    }
}
