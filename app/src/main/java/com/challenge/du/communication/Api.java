package com.challenge.du.communication;


import com.challenge.du.BuildConfig;
import com.challenge.du.communication.response.HomeScreenResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by k.zahid on 11/7/17.
 */

public interface Api {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    Api SERVICE = new Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api.class);

    @GET("19kxcv")
    Call<HomeScreenResponse> GetHomeScreenContent();





}
