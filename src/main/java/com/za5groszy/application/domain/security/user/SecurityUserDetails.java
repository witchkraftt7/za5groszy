package com.za5groszy.application.domain.security.user;

import com.za5groszy.foundation.models.Authority;
import com.za5groszy.foundation.sharedkernel.UserId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUserDetails implements UserDetails {
    private UserId userId;
    private String username;
    private String password;
    private Set<Authority> authorities;

    SecurityUserDetails(UserId userId, String username, String password, Set<Authority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(authority ->
                        new SimpleGrantedAuthority(authority.getName().toString()))
                .collect(Collectors.toList());
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
