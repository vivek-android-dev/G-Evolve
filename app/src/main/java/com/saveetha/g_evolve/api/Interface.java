package com.saveetha.g_evolve.api;

import com.saveetha.g_evolve.responses.AddEducationResponse;
import com.saveetha.g_evolve.responses.AddProductResponse;
import com.saveetha.g_evolve.responses.EditProfileResponse;
import com.saveetha.g_evolve.responses.GetProfileResponse;
import com.saveetha.g_evolve.responses.LoginResponse;
import com.saveetha.g_evolve.responses.RegisterResponse;
import com.saveetha.g_evolve.responses.ShowAllMessageResponse;
import com.saveetha.g_evolve.responses.ShowAllRecyclerResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface Interface {


    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterResponse> register(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("password_confirmation") String password_confirmation);

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("/api/showProfile/{user_id}")
    Call<GetProfileResponse> profile(@Path("user_id") String user_id);


    @Multipart
    @POST("/api/updateProfile")
    Call<EditProfileResponse> updateProfile(@Part("user_id") RequestBody user_id, @Part MultipartBody.Part image, @Part("name") RequestBody name, @Part("email") RequestBody email);


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
    Call<ShowAllRecyclerResponse> editRecycler(@Field("recycler_id") String recycler_id, @Field("company_name") String company_name,
                                               @Field("capacity") String capacity, @Field("address") String address,
                                               @Field("email") String email, @Field("contact") String contact,
                                               @Field("open_time") String open_time, @Field("close_time") String close_time,
                                               @Field("latitude") String latitude, @Field("longitude") String longitude);


    @Multipart
    @POST("/api/addEducation")
    Call<AddEducationResponse> addEducation(@Part("title") RequestBody title, @Part MultipartBody.Part image,
                                            @Part("description") RequestBody description);

    @GET("/api/showAllEducation")
    Call<AddEducationResponse> showAllEducation();


//    @GET("/" + "/" + "api/deleteEducation/{education}")
//    Call<AddEducationResponse> deleteEducation(@Path("education") String education);

//    @FormUrlEncoded
//    @POST("/api/deleteRecycler")
//    Call<ShowAllRecyclerResponse> deleteRecycler(@Field("recycler_id") String recycler_id);
//
//    @FormUrlEncoded
//    @POST("/api/deleteProfile")
//    Call<EditProfileResponse> deleteProfile(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("/api/recyclerLogin")
    Call<LoginResponse> recyclerLogin(@Field("email") String email, @Field("contact") String contact);

    @GET("api/showAllMessages")
    Call<ShowAllMessageResponse> showAllMessages();

    @FormUrlEncoded
    @POST("/api/sendMessage")
    Call<ShowAllMessageResponse> sendMessage(@Field("user_id") String user_id, @Field("message") String message);

//    @FormUrlEncoded
//    @POST("/api/deleteMessage")
//    Call<ShowAllMessageResponse> deleteMessage(@Field("query_id") String query_id);


    @FormUrlEncoded
    @POST("/api/showProduct")
    Call<AddProductResponse> showProduct(@Field("recycler") String recycler);

    @FormUrlEncoded
    @POST("/api/acceptProduct")
    Call<AddProductResponse> acceptProduct(@Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("/api/rejectProduct")
    Call<AddProductResponse> rejectProduct(@Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("/api/addProduct")
    Call<AddProductResponse> addProduct(@Field("user_id") String user_id, @Field("brand") String brand, @Field("model") String model, @Field("price") String price,
                                        @Field("date") String date, @Field("time") String time, @Field("location") String location, @Field("phone") String phone, @Field("recycler") String recycler);


    @DELETE
    Call<AddEducationResponse> deleteEducation(@Url String url);

}
