package com.za5groszy.application.domain.market.presenter;

import com.za5groszy.application.ApplicationEncoderService;
import com.za5groszy.application.websocket.WebSocketMessagePresenter;
import com.za5groszy.foundation.market.domain.exception.MarketException;
import org.json.JSONArray;
import org.json.JSONObject;

public class MarketErrorMessagePresenter implements WebSocketMessagePresenter {
    private static final String ITEM_ID_FIELD = "itemId";
    private static final String MESSAGE_FIELD = "message";
    private static final String STATUS_FIELD = "status";
    private static final String ERROR_STATUS_VALUE = "error";
    private static final String ERROR_CODE_FIELD = "errorCode";

    private MarketException exception;
    private ApplicationEncoderService encoder;

    public MarketErrorMessagePresenter(MarketException exception, ApplicationEncoderService encoder) {
        this.exception = exception;
        this.encoder = encoder;
    }

    @Override
    public JSONArray present() {
        JSONObject response = new JSONObject();
        response.put(ITEM_ID_FIELD, encoder.encode(exception.getItemId().getId()));
        response.put(MESSAGE_FIELD, exception.getMessage());
        response.put(STATUS_FIELD, ERROR_STATUS_VALUE);
        response.put(ERROR_CODE_FIELD, exception.getErrorCode());

        JSONArray responseList = new JSONArray();
        responseList.put(response);

        return responseList;
    }
}
