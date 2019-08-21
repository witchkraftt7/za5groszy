package com.za5groszy.foundation.sharedkernel.infrastructure.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auction")
public class Auction {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    private Item item;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFinishedAt(Date finishesAt) {
        this.finishedAt = finishesAt;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
