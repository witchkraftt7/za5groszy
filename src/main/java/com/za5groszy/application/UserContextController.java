package com.za5groszy.application;

import com.za5groszy.application.configs.websocket.WebSocketConfig;
import com.za5groszy.application.domain.security.user.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

public class UserContextController {
    @Autowired
    protected SimpMessagingTemplate template;

    @Autowired
    protected ApplicationEncoderService encoder;

    @PostConstruct
    private void init() {
        template.setUserDestinationPrefix(WebSocketConfig.USER_TOPIC_PATH);
    }

    protected SecurityUserDetails getUserDetails() {
        return (SecurityUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
