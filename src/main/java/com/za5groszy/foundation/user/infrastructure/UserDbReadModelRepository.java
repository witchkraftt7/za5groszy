package com.za5groszy.foundation.user.infrastructure;

import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.user.domain.User;
import com.za5groszy.foundation.user.readmodel.UserReadModelRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDbReadModelRepository implements UserReadModelRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public User getUserByUsername(String username) {
        Query<com.za5groszy.foundation.models.User> query
                = sessionFactory.getCurrentSession().createQuery(
                        "FROM User u where u.username = :username",
                com.za5groszy.foundation.models.User.class
        );

        query.setParameter("username", username);
        com.za5groszy.foundation.models.User queryResult = query.uniqueResult();

        return new User(
                new UserId(queryResult.getId()),
                queryResult.getUsername(),
                queryResult.getPassword(),
                queryResult.getAuthorities()
        );
    }
}
