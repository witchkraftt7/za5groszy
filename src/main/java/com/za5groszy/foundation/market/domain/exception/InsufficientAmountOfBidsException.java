package com.za5groszy.foundation.market.domain.exception;

import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.exception.PaymentRequiredException;

public class InsufficientAmountOfBidsException extends MarketException implements PaymentRequiredException {
    public InsufficientAmountOfBidsException(UserId userId, ItemId itemId) {
        super(
                "Insufficient amount of bids exception.",
                userId,
                itemId,
                MarketException.INSUFFICIENT_AMOUNT_OF_BIDS_ERROR_CODE
        );
    }
}
