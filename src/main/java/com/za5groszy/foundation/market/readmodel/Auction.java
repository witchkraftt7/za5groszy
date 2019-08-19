package com.za5groszy.foundation.market.readmodel;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;

import java.time.Duration;

public class Auction {
    private UserId winningUserId;
    private ItemId itemId;
    private Duration timeUntilEnd;

    public Auction(UserId winningUserId, ItemId itemId, Duration timeUntilEnd) {
        this.winningUserId = winningUserId;
        this.itemId = itemId;
        this.timeUntilEnd = timeUntilEnd;
    }

    public Duration getTimeUntilEnd() {
        return timeUntilEnd;
    }

    public UserId getWinningUserId() {
        return winningUserId;
    }

    public ItemId getItemId() {
        return itemId;
    }
}
