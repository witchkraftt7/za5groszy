package com.za5groszy.foundation.models;

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

    public void setBidBalance(int bids) {
        this.bidBalance = bids;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

