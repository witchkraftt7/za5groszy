package com.za5groszy.foundation.market.readmodel;

import com.za5groszy.foundation.market.sharedkernel.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketReadModel {
    @Autowired
    private MarketReadModelRepository repository;

    public List<Item> getCurrentState() {
        return repository.getCurrentState();
    }
}
