package com.saveetha.g_evolve.api;

import com.saveetha.g_evolve.responses.EditProfileResponse;
import com.saveetha.g_evolve.responses.GetProfileResponse;
import com.saveetha.g_evolve.responses.LoginResponse;
import com.saveetha.g_evolve.responses.RegisterResponse;
import com.saveetha.g_evolve.responses.ShowAllRecyclerResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

//    @FormUrlEncoded
//    @POST("/api/login")
//    Call<LoginResponse> editProfile(@Multipart("user_id") Body user_id, @Field("name") String name , @Field("email") String email);
//

    @Multipart
    @POST("/api/updateProfile")
    Call<EditProfileResponse> updateProfile(@Part("user_id") RequestBody user_id, @Part MultipartBody.Part image,@Part("name") RequestBody name, @Part("email") RequestBody email);



    @GET("/api/showAllRecycler")
    Call<ShowAllRecyclerResponse> showAllRecycler();



    @FormUrlEncoded
    @POST("/api/addRecycler")
    Call<ShowAllRecyclerResponse> addRecycler(@Field("company_name") String company_name, @Field("capacity") String capacity,
                                              @Field("address") String address, @Field("email") String email,
                                              @Field("contact") String contact, @Field("open_time") String open_time,
                                              @Field("close_time") String close_time, @Field("latitude") String latitude,
                                              @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("/api/editRecycler")
    Call<ShowAllRecyclerResponse> editRecycler(@Field("company_name") String company_name,
                                               @Field("capacity") String capacity, @Field("address") String address,
                                               @Field("email") String email, @Field("contact") String contact,
                                               @Field("open_time") String open_time, @Field("close_time") String close_time,
                                               @Field("latitude") String latitude, @Field("longitude") String longitude);
}
