package com.za5groszy.application.market;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.za5groszy.application.UserContextController;
import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.market.presenter.MarketPresenter;
import com.za5groszy.foundation.market.application.MarketService;
import com.za5groszy.foundation.market.application.command.UpBid;
import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.market.domain.MarketRepository;
import com.za5groszy.foundation.market.domain.exception.InsufficientAmountOfBidsException;
import com.za5groszy.foundation.sharedkernel.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MarketController extends UserContextController {
    @Autowired
    private MarketService service;

    @Autowired
    private MarketRepository repository;

    @MessageMapping("/bid")
    public void upBid(@Payload Message $message, Principal user) throws Exception {
        try {
            Item item = service.upBid(
                    new UpBid(
                            new ItemId(44),
                            new UserId(22)
                    )
            );

            MarketPresenter presenter = new MarketPresenter(
                    item,
                    new ObjectMapper()
            );

            template.convertAndSend(
                    WebSocketConfig.PUBLIC_TOPIC_PATH,
                    presenter.present()
            );
        } catch (InsufficientAmountOfBidsException e) {
            template.convertAndSendToUser(
                    user.getName(),
                    "",
                    "fail"
            );
        }
    }
}
