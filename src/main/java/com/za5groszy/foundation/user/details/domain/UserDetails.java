package com.za5groszy.foundation.user.details.domain;

import com.za5groszy.foundation.sharedkernel.Email;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.Authority;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.user.registration.domain.Password;

import java.util.Set;

final public class UserDetails {
    private UserId userId;
    private Email email;
    private Password password;
    private Set<Authority> authorities;

    public UserDetails(UserId userId, Email email, Password password, Set<Authority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public Password getPassword() {
        return password;
    }

    public UserId getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
