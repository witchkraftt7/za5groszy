package com.za5groszy.foundation.sharedkernel.infrastructure.models;

import javax.persistence.*;

@Entity
@Table(name = "user_bid_balance")
public class UserBidBalance {
    @Id
    private Integer id;

    @Column
    private int bidBalance;

    @OneToOne
    @MapsId
    private User user;

    public int getBidBalance() {
        return bidBalance;
    }

    public User getUser() {
        return user;
    }

    public void setBidBalance(int bidBalance) {
        this.bidBalance = bidBalance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

