package com.za5groszy.application.websocket;

import org.json.simple.JSONObject;

import java.util.List;

public interface WebSocketMessagePresenter {
    public List<JSONObject> present();
}