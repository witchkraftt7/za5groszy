package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.readmodel.MarketReadModelRepository;
import com.za5groszy.foundation.market.sharedkernel.item.Item;
import com.za5groszy.foundation.market.sharedkernel.item.ItemId;
import com.za5groszy.foundation.sharedkernel.UserId;
import com.za5groszy.foundation.sharedkernel.infrastructure.AggregateRepository;
import org.springframework.stereotype.Repository;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MarketReadModelDbRepository extends AggregateRepository implements MarketReadModelRepository {

    public List<Item> getCurrentState() {
        List<com.za5groszy.foundation.models.Item> itemList =
                (List<com.za5groszy.foundation.models.Item>) getCurrentSession()
                        .createSQLQuery("SELECT * FROM items")
                        .addEntity(com.za5groszy.foundation.models.Item.class)
                        .list();

        List<Item> processedItems = new ArrayList<>();
        itemList.parallelStream().forEach(item -> {
            processedItems.add(
                    new Item(
                            new UserId(item.getWinningUser().getId()),
                            new ItemId(item.getId()),
                            Duration.between(Instant.now(), item.getItemBid().getAuctionEndsOn().toInstant())
                    )
            );
        });

        return processedItems;
    }
}
