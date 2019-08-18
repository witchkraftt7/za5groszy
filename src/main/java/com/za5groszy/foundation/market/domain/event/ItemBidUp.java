package com.za5groszy.foundation.market.domain.event;

import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;

import java.time.Duration;

public class ItemBidUp extends AggregateEvent {
    private ItemId itemId;
    private Duration expirationDate;

    public ItemBidUp(Object source, UserId userId, ItemId itemId) {
        super(source, userId);
        this.itemId = itemId;
    }

    public ItemId getItemId() {
        return itemId;
    }

    public ItemBidUp withExpirationDate(Duration expirationDate) {
        ItemBidUp event = (ItemBidUp) this.clone();
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
