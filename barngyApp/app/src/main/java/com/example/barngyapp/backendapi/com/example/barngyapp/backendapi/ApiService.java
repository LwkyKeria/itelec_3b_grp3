package com.example.barngyapp.backendapi;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.example.barngyapp.eventadapter.Event;
public interface ApiService {

    @Headers("Content-type: application/json")
    @POST("routes/insert_data.php")
    Call<ApiResponse> insertUser(@Body User user);

    @POST("routes/android_api_login.php")
    Call<ApiResponse> loginUser(@Body User user);

    @POST("routes/android_createAccount.php")
    Call<ApiResponse> createUser(@Body registerUser user);

    @POST("routes/android_submit_request.php")
    Call<ApiResponse> sendDocumentRequest(@Body DocumentRequest documentRequest);

    @FormUrlEncoded
    @POST("routes/android_create_appointment.php")
    Call<ApiResponse> createAppointment(
            @Field("appointment_reason") String reason,
            @Field("appointment_date") String date,
            @Field("user_ID") String userId,
            @Field("official_id") int officialId  // Updated parameter name
    );

    @GET("routes/android_get_officials.php")
    Call<List<BarangayOfficial>> getBarangayOfficials();

    @GET("routes/android_getEvents.php")
    Call<List<Event>> getEvents();
}