package com.example.bitcot.networkAPI;

import com.google.gson.JsonObject;


public interface RetrofitCallBackListener {
    void retrofitCallBackListener(JsonObject jsonObject, String action);
}