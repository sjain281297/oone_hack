package com.example.shubham.oone_hack_app;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shubham on 27/5/17.
 */

public class ApiClient {

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            Retrofit r = new Retrofit.Builder().baseUrl("https://192.168.4.115:5000/").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();

            apiService = r.create(ApiService.class);

        }
        return apiService;
    }
}
