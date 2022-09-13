package com.nhom1.loginregisterfirebase.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String BASE_URL = "http://192.168.40.1:6868/";
    //https://infortlus.com/php/api_aps.php
    private static final HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor).build();

    public static APSService getApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APSService.class);
    }
}
