package com.za5groszy.application.domain.market;

import com.za5groszy.application.UserContextController;
import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.domain.market.presenter.MarketErrorMessagePresenter;
import com.za5groszy.application.domain.market.presenter.MarketGridPresenter;
import com.za5groszy.foundation.market.application.MarketService;
import com.za5groszy.foundation.market.application.command.BidUp;
import com.za5groszy.foundation.market.domain.event.UserBadeUp;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MarketGridController extends UserContextController {
    private MarketService service;

    @Autowired
    public MarketGridController(MarketService service) {
        this.service = service;
    }

    @MessageMapping("/bid/{itemId}")
    public void bidUp(@DestinationVariable String itemId, Principal user) {
        try {
            UserBadeUp item = service.bidUp(
                    new BidUp(
                            getUserDetails().getUserId(),
                            new ItemId(encoder.decode(itemId))
                    )
            );

            MarketGridPresenter presenter = new MarketGridPresenter(item.getItem(), encoder);

            template.convertAndSend(
                    WebSocketConfig.PUBLIC_TOPIC_PATH,
                    presenter.present()
            );
        } catch (InsufficientAmountOfBidsException | ItemAuctionFinishedException e) {
            MarketErrorMessagePresenter presenter = new MarketErrorMessagePresenter(e, encoder);

            template.convertAndSendToUser(
                    user.getName(),
                    "",
                    presenter.present()
            );
        }
    }
}
