package com.za5groszy.application.configs.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    public static final String PUBLIC_TOPIC_PATH = "/topic/public/";
    public static final String USER_TOPIC_PATH = "/topic/user/";
    private static final String TOPIC_PATH = "/topic";
    private static final String WS_PATH = "/ws";
    private static final String WS_APP_PREFIX = "/app";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(TOPIC_PATH);
        registry.setApplicationDestinationPrefixes(WS_APP_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(WS_PATH)
                .setHandshakeHandler(
                        new DefaultHandshakeHandler(
                                new TomcatRequestUpgradeStrategy()
                        ))
                .setAllowedOrigins("*");
    }
}
