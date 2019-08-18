package com.za5groszy.foundation.user.domain;

import com.za5groszy.foundation.models.Authority;
import com.za5groszy.foundation.sharedkernel.UserId;

import java.util.Set;

public class User {
    private UserId userId;
    private String username;
    private String password;
    private Set<Authority> authorities;

    public User(UserId userId, String username, String password, Set<Authority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
