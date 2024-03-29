package com.za5groszy.foundation.user.registration.infrastructure;

import com.za5groszy.foundation.sharedkernel.Email;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.event.AggregateEvent;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.Authority;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.AuthorityType;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.User;
import com.za5groszy.foundation.user.registration.domain.UserRegistrationRepository;
import com.za5groszy.foundation.user.registration.domain.event.UserRegistered;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRegistrationDbRepository implements UserRegistrationRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public UserRegistrationDbRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean userExists(Email email) {
        Query<User> query = sessionFactory
                .getCurrentSession()
                .createQuery("FROM User u where email = :email", User.class);

        query.setParameter("email", email.getEmail());

        return query.getResultList().size() != 0;
    }

    @Override
    public AggregateEvent persist(AggregateEvent event) {
        if (event instanceof UserRegistered) {
            return onUserRegistered((UserRegistered) event);
        }

        return event;
    }

    private UserRegistered onUserRegistered(UserRegistered event) {
        User user = new User();
        user.setPassword(
                (new BCryptPasswordEncoder()).encode(
                        event.getDetails().getPassword().toString())
        );
        user.setEmail(event.getDetails().getEmail().getEmail());

        Query<Authority> query = sessionFactory
                .getCurrentSession()
                .createQuery("FROM Authority u where name = :name", Authority.class);

        query.setParameter("name", event.getDetails().getAuthorities().iterator().next());
        Authority a= query.getSingleResult();
        user.getAuthorities().add(query.getSingleResult());

        int persistedUserId = (int) sessionFactory.getCurrentSession().save(user);

        return event.withUserId(new UserId(persistedUserId));
    }
}
