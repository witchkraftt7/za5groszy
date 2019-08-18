package com.za5groszy.application.market;

import com.za5groszy.application.UserContextController;
import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.market.presenter.MarketErrorMessagePresenter;
import com.za5groszy.application.market.presenter.MarketPresenter;
import com.za5groszy.foundation.market.application.MarketService;
import com.za5groszy.foundation.market.application.command.UpBid;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MarketController extends UserContextController {
    @Autowired
    private MarketService service;

    @MessageMapping("/bid")
    public void upBid(@Payload Message $message, Principal user) {
        try {
            // TODO: use real user id and item id
            Item item = service.upBid(
                    new UpBid(
                            userId,
                            new ItemId(44)
                    )
            );

            MarketPresenter presenter = new MarketPresenter(item);

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
