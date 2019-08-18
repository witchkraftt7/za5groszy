package com.za5groszy.foundation.market.infrastructure;

import com.za5groszy.foundation.market.readmodel.MarketReadModelRepository;
import com.za5groszy.foundation.market.sharedkernel.item.Item;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MarketReadModelDbRepository implements MarketReadModelRepository {

    public List<Item> getCurrentState() {
        return new ArrayList<Item>() ;
    }
}
