package com.za5groszy.application.domain.security.user;

import com.za5groszy.foundation.user.domain.User;
import com.za5groszy.foundation.user.readmodel.UserReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    UserReadModel readModel;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = readModel.getUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new SecurityUserDetails(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
