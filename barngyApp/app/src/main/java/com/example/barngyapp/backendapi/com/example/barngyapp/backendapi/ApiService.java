package com.example.barngyapp.backendapi;

import com.example.barngyapp.eventadapter.Event;

import java.util.HashMap;
import java.util.List;

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

    @POST("create_event.php") // Replace with the correct backend endpoint
    Call<ApiResponse> createEvent(@Body HashMap<String, String> event);

    @GET("get_events.php")
    Call<List<Event>> getEvents();

    @POST("routes/create_barangay_info.php")
    Call<ApiResponse> createBarangayInfo(@Body HashMap<String, String> barangayInfo);


}
