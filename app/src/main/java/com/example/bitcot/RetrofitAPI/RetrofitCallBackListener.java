package com.example.bitcot.RetrofitAPI;

import com.google.gson.JsonObject;


public interface RetrofitCallBackListener {
    void retrofitCallBackListener(JsonObject jsonObject, String action);
}