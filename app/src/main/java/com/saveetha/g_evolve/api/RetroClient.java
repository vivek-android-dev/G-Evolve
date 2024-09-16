package com.saveetha.g_evolve.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    public static final String BASE_URL = "https://1e69-2409-408d-80-b8ba-a96d-6077-2d2d-7886.ngrok-free.app";

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)  //Change server URL
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static Interface makeApi() {
        Interface api = getRetrofit().create(Interface.class);
        return api;
    }
}
