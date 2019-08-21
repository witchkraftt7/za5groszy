package com.za5groszy.foundation.user.registration.domain;

import com.za5groszy.foundation.sharedkernel.Email;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;

public interface UserRegistrationRepository extends AggregateRepository {
    boolean userExists(Email email);
}
