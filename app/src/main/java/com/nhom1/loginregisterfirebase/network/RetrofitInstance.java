package com.nhom1.loginregisterfirebase.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String BASE_URL = "https://a22f-118-71-221-92.ap.ngrok.io/";
    //https://a22f-118-71-221-92.ap.ngrok.io/api/v1/register
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
