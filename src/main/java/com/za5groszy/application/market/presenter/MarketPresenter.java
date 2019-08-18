package com.za5groszy.application.market.presenter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.za5groszy.application.websocket.WebSocketMessagePresenter;
import com.za5groszy.foundation.market.sharedkernel.item.Item;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarketPresenter implements WebSocketMessagePresenter {
    private static final String ITEM_ID = "itemId";
    private static final String WINNING_BID = "winningBid";
    private static final String TIME_TILL_END = "timeTillEnd";

    private List<Item> list;

    public MarketPresenter(List<Item> list) {
        this.list = list;
    }

    public MarketPresenter(Item item) {
        this.list = new ArrayList<>();
        this.list.add(item);
    }

    @Override
    public List<JSONObject> present() {
        List<JSONObject> response = new ArrayList<>();

        this.list.parallelStream().forEach(item -> {
            response.add(
                    buildJsonObject(item)
            );
        });

        return response;
    }

    private JSONObject buildJsonObject(Item item) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ITEM_ID, item.getItemId());
        jsonObject.put(WINNING_BID, item.getWinningUserId());
        jsonObject.put(TIME_TILL_END, item.getTimeUntilEnd().getSeconds());

        return jsonObject;
    }
}
