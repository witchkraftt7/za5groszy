package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.readmodel.Auction;
import com.za5groszy.foundation.market.readmodel.MarketReadModelRepository;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class MarketReadModelDbRepository implements MarketReadModelRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Auction> getCurrentState() {
        Query<com.za5groszy.foundation.models.Auction> query
                = sessionFactory.getCurrentSession().createQuery(
                "FROM Auction a WHERE a.finishesAt > :now",
                com.za5groszy.foundation.models.Auction.class
        );

        query.setParameter("now", (new Date()), TemporalType.TIMESTAMP);
        List<com.za5groszy.foundation.models.Auction> queryResult = query.getResultList();

        return buildCurrentState(queryResult);
    }

    private ArrayList<Auction> buildCurrentState(List<com.za5groszy.foundation.models.Auction> list) {
        ArrayList<Auction> currentState = new ArrayList<>();
        list.forEach(item -> {
            currentState.add(new Auction(
                            new UserId(item.getUser().getId()),
                            new ItemId(item.getItem().getId()),
                            Duration.between(Instant.now(), item.getFinishesAt().toInstant())
                    )
            );
        });

        return currentState;
    }
}
