package com.za5groszy.foundation.user.readmodel;

import com.za5groszy.foundation.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReadModel {
    @Autowired
    UserReadModelRepository repository;

    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }
}
