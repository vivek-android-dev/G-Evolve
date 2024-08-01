package com.example.g_evolve.api;

import com.example.g_evolve.responses.GetProfileResponse;
import com.example.g_evolve.responses.LoginResponse;
import com.example.g_evolve.responses.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Interface {

    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterResponse> register(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("password_confirmation") String password_confirmation);

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("/api/showProfile/{user_id}")
    Call<GetProfileResponse> profile(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> editProfile(@Field("user_id") String user_id, @Field("name") String name ,@Field("email") String email);





}
