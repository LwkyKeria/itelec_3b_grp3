package  com.example.barngyapp.backendapi;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface ApiService {
    @Headers("Content-type: application/json")
    @POST("routes/insert_data.php")
    Call<ApiResponse> insertUser(@Body User user);

    @POST("routes/android_api_login.php")
    Call<ApiResponse> loginUser(@Body User user);
}
