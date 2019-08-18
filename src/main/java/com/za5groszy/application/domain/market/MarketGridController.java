package com.za5groszy.application.domain.market;

import com.za5groszy.application.UserContextController;
import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.market.presenter.MarketErrorMessagePresenter;
import com.za5groszy.application.market.presenter.MarketGridPresenter;
import com.za5groszy.foundation.market.application.MarketService;
import com.za5groszy.foundation.market.application.command.BidUp;
import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MarketGridController extends UserContextController {
    @Autowired
    private MarketService service;

    @MessageMapping("/bid")
    public void upBid(@Payload Message $message, Principal user) {
        try {
            // TODO: use real user id and item id
            UserBadeUp item = service.bidUp(
                    new BidUp(
                            getUserDetails().getUserId(),
                            new ItemId(44)
                    )
            );

            MarketGridPresenter presenter = new MarketGridPresenter(item.getItem());

            template.convertAndSend(
                    WebSocketConfig.PUBLIC_TOPIC_PATH,
                    presenter.present().toString()
            );
        } catch (InsufficientAmountOfBidsException | ItemAuctionFinishedException e) {
            MarketErrorMessagePresenter presenter = new MarketErrorMessagePresenter(e);

            template.convertAndSendToUser(
                    user.getName(),
                    "",
                    presenter.present().toString()
            );
        }
    }
}
