package com.za5groszy.foundation.market.readmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketReadModel {
    @Autowired
    private MarketReadModelRepository repository;

    public List<Auction> getCurrentState() {
        return repository.getCurrentState();
    }
}
