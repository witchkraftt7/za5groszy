package com.za5groszy.foundation.user.registration.application;

import com.za5groszy.foundation.user.registration.application.command.RegisterUser;
import com.za5groszy.foundation.user.registration.domain.RegisteredUsers;
import com.za5groszy.foundation.user.registration.domain.event.UserRegistered;
import com.za5groszy.foundation.user.registration.domain.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private RegisteredUsers registeredUsers;

    @Autowired
    public UserRegistrationService(RegisteredUsers registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public UserRegistered registerUser(RegisterUser command) throws UserAlreadyExistsException {
        return registeredUsers.add(
                command.getDetails()
        );
    }
}
