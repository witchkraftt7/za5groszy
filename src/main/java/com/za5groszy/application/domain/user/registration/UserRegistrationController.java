package com.za5groszy.application.domain.user.registration;

import com.za5groszy.application.domain.user.registration.request.UserRegistrationRequest;
import com.za5groszy.application.error.ErrorResponse;
import com.za5groszy.foundation.sharedkernel.Email;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.exception.DomainModelException;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.Authority;
import com.za5groszy.foundation.sharedkernel.infrastructure.models.AuthorityType;
import com.za5groszy.foundation.user.details.domain.UserDetails;
import com.za5groszy.foundation.user.registration.application.UserRegistrationService;
import com.za5groszy.foundation.user.registration.application.command.RegisterUser;
import com.za5groszy.foundation.user.registration.domain.event.UserRegistered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class UserRegistrationController {
    @Autowired
    UserRegistrationService service;

    @PostMapping("/users")
    public ResponseEntity registerUser(@Valid @RequestBody final UserRegistrationRequest request, final BindingResult bindingResult) {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority(AuthorityType.ROLE_USER);
        authorities.add(authority);

        if (bindingResult.getErrorCount() > 0) {
            return ErrorResponse.validationError(bindingResult.getFieldErrors());
        }

        try {
            UserRegistered event = service.registerUser(
                    new RegisterUser(
                            new UserDetails(
                                    UserId.nullInstance(),
                                    new Email(request.getEmail()),
                                    request.getPassword(),
                                    authorities
                            )
                    )
            );

            return new ResponseEntity<>(event.getDetails(), HttpStatus.CREATED);
        } catch (DomainModelException exception) {

            return ErrorResponse.domainError(exception);
        }
    }
}
