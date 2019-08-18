package com.za5groszy.application.market;

import com.za5groszy.application.configs.websocket.WebSocketConfig;

import com.za5groszy.application.market.presenter.MarketPresenter;
import com.za5groszy.foundation.market.readmodel.MarketReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketMessageScheduler {
    private static final int MESSAGE_PUSH_DELAY_MS = 1000;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MarketReadModel readModel;

    @Scheduled(fixedDelay = MESSAGE_PUSH_DELAY_MS)
    public void pushMarketState() {
        MarketPresenter presenter = new MarketPresenter(
                readModel.getCurrentState()
        );

        template.convertAndSend(
                WebSocketConfig.PUBLIC_TOPIC_PATH,
                presenter.present()
        );
    }
}
