package com.za5groszy.foundation.user.registration.domain;

import com.za5groszy.foundation.user.details.domain.UserDetails;
import com.za5groszy.foundation.user.registration.domain.event.UserRegistered;
import com.za5groszy.foundation.user.registration.domain.event.UserRegistrationEventEmitter;
import com.za5groszy.foundation.user.registration.domain.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUsers {
    @Autowired
    private UserRegistrationEventEmitter emitter;

    @Autowired
    private UserRegistrationRepository repository;

    public RegisteredUsers(UserRegistrationRepository repository, UserRegistrationEventEmitter emitter) {
        this.repository = repository;
        this.emitter = emitter;
    }

    public UserRegistered add(UserDetails details) throws UserAlreadyExistsException {
        if (repository.userExists(details.getEmail())) {
            throw new UserAlreadyExistsException(details.getEmail());
        }

        return (UserRegistered) emitter.emit(
                new UserRegistered(
                        details
                )
        );
    }
}
