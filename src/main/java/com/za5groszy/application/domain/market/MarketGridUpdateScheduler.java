package com.za5groszy.application.domain.market;

import com.za5groszy.application.ApplicationEncoderService;
import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.domain.market.presenter.MarketGridPresenter;
import com.za5groszy.foundation.market.readmodel.MarketReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketGridUpdateScheduler {
    private static final int MESSAGE_PUSH_DELAY_MS = 1000;

    private SimpMessagingTemplate template;
    private MarketReadModel readModel;
    private ApplicationEncoderService encoder;

    @Autowired
    public MarketGridUpdateScheduler(
            SimpMessagingTemplate template,
            MarketReadModel readModel,
            ApplicationEncoderService encoder
    ) {
        this.template = template;
        this.readModel = readModel;
        this.encoder = encoder;
    }

    @Scheduled(fixedDelay = MESSAGE_PUSH_DELAY_MS)
    public void pushMarketState() {
        MarketGridPresenter presenter = new MarketGridPresenter(
                readModel.getCurrentState(),
                encoder
        );

        template.convertAndSend(
                WebSocketConfig.PUBLIC_TOPIC_PATH,
                presenter.present()
        );
    }
}
