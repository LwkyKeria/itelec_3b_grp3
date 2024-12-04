package com.example.barngyapp.backendapi;

import com.example.barngyapp.eventadapter.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @POST("routes/android_submit_request.php")  // Update the path to your actual PHP script
    Call<ApiResponse> sendDocumentRequest(@Body DocumentRequest documentRequest);

    @GET("routes/event.php")
    Call<List<Event>> getEvents();
}
