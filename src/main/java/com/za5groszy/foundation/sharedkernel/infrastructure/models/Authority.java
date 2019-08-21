package com.za5groszy.foundation.sharedkernel.infrastructure.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    @ManyToMany
    private Set<User> users;

    public Authority() {
    }

    public Authority(AuthorityType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }
}
