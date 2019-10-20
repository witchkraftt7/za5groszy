package com.za5groszy.application.domain.market.presenter;

import com.za5groszy.application.ApplicationEncoderService;
import com.za5groszy.application.websocket.WebSocketMessagePresenter;
import com.za5groszy.foundation.market.readmodel.Auction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarketGridPresenter implements WebSocketMessagePresenter {
    private static final String ITEM_ID = "itemId";
    private static final String WINNING_USER = "winningUser";
    private static final String TIME_TILL_END = "timeTillEnd";

    private List<Auction> list;
    private ApplicationEncoderService encoder;

    public MarketGridPresenter(List<Auction> list, ApplicationEncoderService encoder) {
        this.list = list;
        this.encoder = encoder;
    }

    public MarketGridPresenter(Auction item, ApplicationEncoderService encoder) {
        this.list = new ArrayList<>();
        this.list.add(item);
        this.encoder = encoder;
    }

    @Override
    public List<JSONObject> present() {
        List<JSONObject> response = new ArrayList<>();
        this.list.forEach(item -> {
            response.add(
                    buildJsonObject(item)
            );
        });

        return response;
    }

    private JSONObject buildJsonObject(Auction item) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ITEM_ID, encoder.encode(item.getItemId().getId()));
        jsonObject.put(WINNING_USER, encoder.encode(item.getWinningUserId().getId()));
        jsonObject.put(TIME_TILL_END, item.getTimeUntilEnd().getSeconds());

        return jsonObject;
    }
}
