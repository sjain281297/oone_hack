package com.example.shubham.oone_hack_app;

import android.test.suitebuilder.annotation.LargeTest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by shubham on 27/5/17.
 */

public interface ApiService {

    @POST("api/loginauth")
    Call<Token> getToken(@Body AuthBody body);


}
