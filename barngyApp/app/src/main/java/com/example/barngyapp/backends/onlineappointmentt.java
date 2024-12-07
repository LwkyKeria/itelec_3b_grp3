package com.example.barngyapp.backends;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.ApiClient;
import com.example.barngyapp.backendapi.ApiResponse;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.BarangayOfficial;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class onlineappointmentt extends AppCompatActivity {

    private EditText editReason;
    private EditText editTextDate;
    private Spinner barangaySpinner;
    private String userId;
    private int officialId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlineappointment);

        // Initialize views
        editReason = findViewById(R.id.reason);
        editTextDate = findViewById(R.id.selectedDate);
        barangaySpinner = findViewById(R.id.barangay_officials);

        Button btnreq = findViewById(R.id.reqForApp);
        Button btnDatePicker = findViewById(R.id.datePickerButton);

        // Retrieve user ID from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = prefs.getString("user_id", null);

        if (userId == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Load barangay officials
        loadBarangayOfficials();

        // Set up the spinner listener
        barangaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BarangayOfficial selectedOfficial = (BarangayOfficial) parent.getItemAtPosition(position);
                officialId = selectedOfficial.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                officialId = -1;
            }
        });

        // Set button click listeners
        btnreq.setOnClickListener(v -> validateInput());
        btnDatePicker.setOnClickListener(this::openDatePicker);
    }

    private void loadBarangayOfficials() {
        // Initially set up temporary data while loading
        List<BarangayOfficial> barangayOfficials = new ArrayList<>();
        barangayOfficials.add(new BarangayOfficial(1, "Loading...", "", ""));

        ArrayAdapter<BarangayOfficial> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, barangayOfficials);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        barangaySpinner.setAdapter(adapter);

        // Make API call to get officials
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BarangayOfficial>> call = apiService.getBarangayOfficials();

        call.enqueue(new Callback<List<BarangayOfficial>>() {
            @Override
            public void onResponse(Call<List<BarangayOfficial>> call, Response<List<BarangayOfficial>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BarangayOfficial> officials = response.body();
                    ArrayAdapter<BarangayOfficial> newAdapter = new ArrayAdapter<>(
                            onlineappointmentt.this,
                            android.R.layout.simple_spinner_item,
                            officials
                    );
                    newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    barangaySpinner.setAdapter(newAdapter);
                } else {
                    Toast.makeText(onlineappointmentt.this,
                            "Failed to load officials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BarangayOfficial>> call, Throwable t) {
                Toast.makeText(onlineappointmentt.this,
                        "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDatePicker(View v) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                onlineappointmentt.this,
                (view, year1, month1, dayOfMonth1) -> {
                    String selectedDate = String.format("%04d-%02d-%02d", year1, month1 + 1, dayOfMonth1);
                    editTextDate.setText(selectedDate);
                },
                year, month, dayOfMonth);

        // Set minimum date to today
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    private void validateInput() {
        String reason = editReason.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();

        if (officialId == -1) {
            Toast.makeText(this, "Please select a Barangay Official", Toast.LENGTH_SHORT).show();
            return;
        }

        if (reason.isEmpty()) {
            editReason.setError("Please enter a reason for the appointment");
            return;
        }

        if (date.isEmpty()) {
            editTextDate.setError("Please select a date");
            return;
        }

        // Validate date
        try {
            Calendar selectedDate = Calendar.getInstance();
            String[] dateParts = date.split("-");
            selectedDate.set(
                    Integer.parseInt(dateParts[0]), // year
                    Integer.parseInt(dateParts[1]) - 1, // month (0-based)
                    Integer.parseInt(dateParts[2]) // day
            );

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            if (selectedDate.before(today)) {
                Toast.makeText(this, "Please select a future date", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Invalid date format", Toast.LENGTH_SHORT).show();
            return;
        }

        requestAppointment(reason, date);
    }

    private void requestAppointment(String reason, String date) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<ApiResponse> call = apiService.createAppointment(reason, date, userId, officialId);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();

                    if ("success".equals(apiResponse.getStatus())) {
                        Toast.makeText(onlineappointmentt.this,
                                apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(onlineappointmentt.this,
                                apiResponse.getMessage() != null ?
                                        apiResponse.getMessage() :
                                        "Failed to submit the appointment request",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(onlineappointmentt.this,
                            "Error: Server response was not successful",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(onlineappointmentt.this,
                        "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}