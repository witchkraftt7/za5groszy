package com.za5groszy.foundation.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "user_bid_balance")
public class UserBidBalance {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @NaturalId
    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;

    @Column(name = "bid_balance")
    private int bidBalance;

    @OneToOne
    @MapsId
    private User user;

    public int getUserId() {
        return userId;
    }

    public int getBidBalance() {
        return bidBalance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBidBalance(int bidBalance) {
        this.bidBalance = bidBalance;
    }

    public int getId() {
        return id;
    }
}
