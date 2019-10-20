package com.za5groszy.foundation.user.details.infrastructure;

import com.za5groszy.foundation.sharedkernel.Email;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.user.details.domain.UserDetails;
import com.za5groszy.foundation.user.details.readmodel.UserDetailsReadModelRepository;
import com.za5groszy.foundation.user.registration.domain.Password;
import com.za5groszy.foundation.user.registration.domain.exception.InvalidPasswordStrengthException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDbReadModelRepository implements UserDetailsReadModelRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDetailsDbReadModelRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDetails getUserByUsername(String username) {
        Query<com.za5groszy.foundation.sharedkernel.infrastructure.models.User> query
                = sessionFactory.getCurrentSession().createQuery(
                "FROM User u where u.email = :username",
                com.za5groszy.foundation.sharedkernel.infrastructure.models.User.class
        );

        query.setParameter("username", username);
        com.za5groszy.foundation.sharedkernel.infrastructure.models.User queryResult = query.uniqueResult();

        if (queryResult == null) {
            return null;
        }
        try {
            return new UserDetails(
                    new UserId(queryResult.getId()),
                    new Email(queryResult.getEmail()),
                    new Password(queryResult.getPassword()),
                    queryResult.getAuthorities()
            );
        } catch (InvalidPasswordStrengthException exception) {
            // persisted password is always correct
            return null;
        }
    }
}
