package com.za5groszy.foundation.user.readmodel;

import com.za5groszy.foundation.user.domain.User;

public interface UserReadModelRepository {
    public User getUserByUsername(String username);
}
