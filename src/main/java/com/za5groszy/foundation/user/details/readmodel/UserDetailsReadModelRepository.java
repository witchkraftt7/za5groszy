package com.za5groszy.foundation.user.details.readmodel;

import com.za5groszy.foundation.user.details.domain.UserDetails;

public interface UserDetailsReadModelRepository {
    UserDetails getUserByUsername(String username);
}
