package com.example.bitcot.RetrofitAPI;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ApiService {
    String TAG = "ApiService";

    @Headers("Content-Type: application/json")
    @GET("breeds/list/all/")
    Call<JsonObject> getBreedList();
}
