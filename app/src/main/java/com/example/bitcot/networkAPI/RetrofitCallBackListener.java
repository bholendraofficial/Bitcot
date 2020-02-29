package com.example.bitcot.networkAPI;

import com.google.gson.JsonObject;

import org.json.JSONException;


public interface RetrofitCallBackListener {
    void retrofitCallBackListener(JsonObject jsonObject, String action) throws JSONException;
}