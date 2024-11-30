package com.example.barngyapp.adminapplicationAdapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.RetrofitClient;
import com.example.barngyapp.backendapi.ApiResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admin_eventmaker extends AppCompatActivity {

    private EditText etEventTitle, etEventLocation;
    private DatePicker datePicker;
    private Button btnCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_event_maker);

        // Initialize views
        etEventTitle = findViewById(R.id.etEventTitle);
        etEventLocation = findViewById(R.id.etEventLocation);
        datePicker = findViewById(R.id.datePicker);
        btnCreateEvent = findViewById(R.id.btnCreateEvent);

        // Validate views
        if (etEventTitle == null || etEventLocation == null || datePicker == null || btnCreateEvent == null) {
            throw new RuntimeException("One or more views not found. Check your XML layout IDs.");
        }

        btnCreateEvent.setOnClickListener(v -> {
            String title = etEventTitle.getText().toString().trim();
            String location = etEventLocation.getText().toString().trim();

            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1; // Month starts from 0
            int year = datePicker.getYear();
            String date = day + "/" + month + "/" + year;

            if (!title.isEmpty() && !location.isEmpty()) {
                createEvent(title, location, date);
            } else {
                Toast.makeText(admin_eventmaker.this, "All fields are required", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createEvent(String title, String location, String date) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Prepare the data to be sent
        HashMap<String, String> event = new HashMap<>();
        event.put("title", title);
        event.put("location", location);
        event.put("date", date);

        Call<ApiResponse> call = apiService.createEvent(event);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    Log.d("createEvent", "Response: " + apiResponse.getMessage());
                    if (apiResponse.isSuccess()) {
                        Toast.makeText(admin_eventmaker.this, "Event Created Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(admin_eventmaker.this, "Failed to create event: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("createEvent", "Response failed: " + response.errorBody());
                    Toast.makeText(admin_eventmaker.this, "Failed to create event. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("createEvent", "Error: " + t.getMessage(), t);
                Toast.makeText(admin_eventmaker.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
