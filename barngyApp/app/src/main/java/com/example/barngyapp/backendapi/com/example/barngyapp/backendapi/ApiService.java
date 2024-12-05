package com.example.barngyapp.backendapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers("Content-type: application/json")
    @POST("routes/insert_data.php")
    Call<ApiResponse> insertUser(@Body User user);

    @POST("routes/android_api_login.php")
    Call<ApiResponse> loginUser(@Body User user);

    @POST("routes/android_createAccount.php")
    Call<ApiResponse> createUser(@Body registerUser user);

    // Updated sendDocumentRequest method
    @POST("routes/android_submit_request.php")
    Call<ApiResponse> sendDocumentRequest(@Body DocumentRequest documentRequest);

    @FormUrlEncoded
    @POST("routes/insert_data.php")
    Call<ApiResponse> createAppointment(
            @Field("appointment_reason") String reason,
            @Field("appointment_date") String date,
            @Field("user_ID") int userId,
            @Field("appointment_bgryofficials") int officialId
    );
}