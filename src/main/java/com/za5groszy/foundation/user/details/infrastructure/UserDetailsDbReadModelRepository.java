package com.za5groszy.foundation.user.details.infrastructure;

import com.za5groszy.foundation.sharedkernel.Email;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.user.details.domain.UserDetails;
import com.za5groszy.foundation.user.details.readmodel.UserDetailsReadModelRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDbReadModelRepository implements UserDetailsReadModelRepository {
    @Autowired
    private SessionFactory sessionFactory;

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

        return new UserDetails(
                new UserId(queryResult.getId()),
                new Email(queryResult.getEmail()),
                queryResult.getPassword(),
                queryResult.getAuthorities()
        );
    }
}
