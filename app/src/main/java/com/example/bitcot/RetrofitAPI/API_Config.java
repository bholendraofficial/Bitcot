package com.example.bitcot.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Config {

    private static String URL = "https://dog.ceo/api/";

    public static ApiService getAPIClientByGet() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(ApiService.class);
    }
}
