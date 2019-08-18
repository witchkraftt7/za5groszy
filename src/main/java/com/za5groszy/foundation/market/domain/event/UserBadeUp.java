package com.za5groszy.foundation.market.domain.event;

import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;

import java.time.Duration;

public class UserBadeUp extends AggregateEvent {
    private ItemId itemId;
    private Duration expirationDate;

    public UserBadeUp(UserId userId, ItemId itemId) {
        super(userId);
        this.itemId = itemId;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public UserBadeUp withExpirationDate(Duration expirationDate) {
        UserBadeUp event = (UserBadeUp) this.clone();
        event.expirationDate = expirationDate;

        return event;
    }

    public Item getItem() {

        return new Item(
                getUserId(),
                getItemId(),
                this.expirationDate
        );
    }
}
