package com.za5groszy.application.market;

import com.za5groszy.application.UserContextController;
import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.market.presenter.MarketErrorMessagePresenter;
import com.za5groszy.application.market.presenter.MarketGridPresenter;
import com.za5groszy.foundation.market.application.MarketService;
import com.za5groszy.foundation.market.application.command.UpBid;
import com.za5groszy.foundation.market.domain.event.ItemBidUp;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.market.domain.exception.ItemAuctionFinishedException;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.user.identity.application.UserIdentityService;
import com.za5groszy.foundation.user.identity.application.command.AuthenticateUser;
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

    @Autowired
    private UserIdentityService identityService;

    @MessageMapping("/bid")
    public void upBid(@Payload Message $message, Principal user) {
        try {
            identityService.authenticate(
                    new AuthenticateUser(
                            getUserDetails().getUserId()
                    )
            );

            // TODO: use real user id and item id
            ItemBidUp item = service.upBid(
                    new UpBid(
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
