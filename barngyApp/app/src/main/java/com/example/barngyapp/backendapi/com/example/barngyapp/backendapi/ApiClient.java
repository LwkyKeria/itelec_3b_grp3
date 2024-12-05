package com.example.barngyapp.backendapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // Base URL of your API
    private static final String BASE_URL = "https://barangayapp.x10.mx/api/"; // Replace with your actual base URL
    private static Retrofit retrofit = null;

    // Method to get the Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Set the base URL for the API
                    .addConverterFactory(GsonConverterFactory.create()) // Add converter to handle JSON response
                    .build();
        }
        return retrofit;
    }
}
