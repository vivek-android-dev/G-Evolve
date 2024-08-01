package com.example.g_evolve.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    public static final String BASE_URL = "https://7289-2401-4900-7b87-e444-d5e5-8a0d-9fff-7237.ngrok-free.app";

    private static Retrofit getRetrofit()
    {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)  //Change server URL
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static Interface makeApi()
    {
        Interface api = getRetrofit().create(Interface.class);
        return api;
    }
}
