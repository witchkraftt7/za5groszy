package com.za5groszy.application.event.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener {
    @EventListener
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String s = "it works!";
    }
}
