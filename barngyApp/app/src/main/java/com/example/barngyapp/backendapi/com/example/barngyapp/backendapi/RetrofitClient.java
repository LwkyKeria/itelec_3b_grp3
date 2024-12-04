package com.example.barngyapp.backendapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://barangayapp.x10.mx/api/";

    // Get the Retrofit instance as a singleton
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            synchronized (RetrofitClient.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)  // Use the base URL of your API
                            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for parsing JSON
                            .build();
                }
            }
        }
        return retrofit;
    }

    // Get the ApiService to make network requests
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
