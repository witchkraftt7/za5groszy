package com.za5groszy.application.domain.user.registration.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserRegistrationRequest {
    @Email
    private String email;

    @NotEmpty
    private String password;

    public UserRegistrationRequest(){}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
