package com.za5groszy.application.event.listeners;

import com.za5groszy.foundation.user.identity.domain.event.UserAuthenticated;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventSubscriber {
    @EventListener
    public void onApplicationEvent(UserAuthenticated event) {
        String s = "it works!";
    }
}
