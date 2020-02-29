/*
 * Copyright (c) 2020. Bholendra Singh
 */

package com.example.bitcot.networkAPI;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("breeds/list/all/")
    Call<JsonObject> getBreedList();

    @Headers("Content-Type: application/json")
    @GET("breeds/image/random")
    Call<JsonObject> getRandomImage();
}
