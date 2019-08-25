package com.za5groszy.foundation.market.readmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
final public class MarketReadModel {
    private MarketReadModelRepository repository;

    @Autowired
    public MarketReadModel(MarketReadModelRepository repository) {
        this.repository = repository;
    }

    public List<Auction> getCurrentState() {
        return repository.getCurrentState();
    }
}
