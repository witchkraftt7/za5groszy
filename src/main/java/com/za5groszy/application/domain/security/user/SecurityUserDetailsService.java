package com.za5groszy.application.domain.security.user;

import com.za5groszy.foundation.user.details.domain.UserDetails;
import com.za5groszy.foundation.user.details.readmodel.UserDetailsReadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private UserDetailsReadModel readModel;

    @Autowired
    public SecurityUserDetailsService(UserDetailsReadModel readModel) {
        this.readModel = readModel;
    }

    @Transactional(readOnly = true)
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails user = readModel.getUserByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        return new SecurityUserDetails(
                user.getUserId(),
                user.getEmail().getEmail(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
