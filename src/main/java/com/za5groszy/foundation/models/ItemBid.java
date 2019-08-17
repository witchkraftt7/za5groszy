package com.za5groszy.foundation.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item_bids")
public class ItemBid {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NaturalId
    @Column(name = "item_id", updatable = false, insertable = false)
    private int itemId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "auction_ends_on")
    private Date auctionEndsOn;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_on")
    private Date createdOn;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public int getItemId() {
        return itemId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getAuctionEndsOn() {
        return auctionEndsOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAuctionEndsOn(Date auctionEndsOn) {
        this.auctionEndsOn = auctionEndsOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
