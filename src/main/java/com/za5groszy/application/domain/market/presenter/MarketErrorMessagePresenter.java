package com.za5groszy.application.market.presenter;

import com.za5groszy.application.websocket.WebSocketMessagePresenter;
import com.za5groszy.foundation.market.domain.exception.MarketException;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MarketErrorMessagePresenter implements WebSocketMessagePresenter {
    private static final String ITEM_ID_FIELD = "itemId";
    private static final String MESSAGE_FIELD = "message";
    private static final String STATUS_FIELD = "status";
    private static final String ERROR_STATUS_VALUE = "error";
    private static final String ERROR_CODE_FIELD = "errorCode";

    private MarketException exception;

    public MarketErrorMessagePresenter(MarketException exception) {
        this.exception = exception;
    }

    @Override
    public List<JSONObject> present() {
        JSONObject response = new JSONObject();
        response.put(ITEM_ID_FIELD, exception.getItemId().getId());
        response.put(MESSAGE_FIELD, exception.getMessage());
        response.put(STATUS_FIELD, ERROR_STATUS_VALUE);
        response.put(ERROR_CODE_FIELD, exception.getErrorCode());

        ArrayList<JSONObject> responseList = new ArrayList<>();
        responseList.add(response);

        return responseList;
    }
}
