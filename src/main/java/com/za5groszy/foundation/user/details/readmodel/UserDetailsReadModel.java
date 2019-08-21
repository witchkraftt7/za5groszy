package com.za5groszy.foundation.user.details.readmodel;

import com.za5groszy.foundation.user.details.domain.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsReadModel {
    @Autowired
    UserDetailsReadModelRepository repository;

    public UserDetails getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }
}
