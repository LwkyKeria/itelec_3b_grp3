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
        editTextDate = findViewById(R.id.selectedDate); // EditText to display the date
        barangaySpinner = findViewById(R.id.barangay_officials); // Spinner for officials

        Button btnreq = findViewById(R.id.reqForApp);
        Button btnDatePicker = findViewById(R.id.datePickerButton);

        // Retrieve user ID from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = prefs.getString("user_id", null); // Fetch the stored user_id

        if (userId == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();  // Close activity if no user is logged in
        }

        // Initialize the list of Barangay Officials (This should be dynamically fetched in production)
        List<BarangayOfficial> barangayOfficials = new ArrayList<>();
        barangayOfficials.add(new BarangayOfficial(1, "Rosauro", "Punong barangay", "contact"));
        barangayOfficials.add(new BarangayOfficial(2, "Marlon", "Sangguniang Barangay Member", "contact"));
        // More officials...

        // Create an ArrayAdapter for the Spinner
        ArrayAdapter<BarangayOfficial> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, barangayOfficials);

        // Set the drop-down view style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter to the Spinner
        barangaySpinner.setAdapter(adapter);

        // Set up the spinner listener
        barangaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BarangayOfficial selectedOfficial = (BarangayOfficial) parent.getItemAtPosition(position);
                officialId = selectedOfficial.getId(); // Get officialId from the selected official
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle if no item is selected
            }
        });

        // Set button click listeners
        btnreq.setOnClickListener(v -> validateInput());
        btnDatePicker.setOnClickListener(this::openDatePicker);
    }

    // Method to handle date picker dialog
    private void openDatePicker(View v) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                onlineappointmentt.this,
                (view, year1, month1, dayOfMonth1) -> {
                    // Format and display the selected date in the required format (YYYY-MM-DD)
                    String selectedDate = year1 + "-" + (month1 + 1) + "-" + dayOfMonth1;
                    editTextDate.setText(selectedDate);  // Update the EditText with selected date
                },
                year, month, dayOfMonth);

        datePickerDialog.show();  // Show the DatePickerDialog
    }

    // Validate inputs before submitting the appointment request
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

        // Proceed to request an appointment if validation passes
        requestAppointment(reason, date);
    }

    // Make API request to create an appointment
    private void requestAppointment(String reason, String date) {
        // Show progress or waiting dialog if needed (optional)
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<ApiResponse> call = apiService.createAppointment(reason, date, Integer.parseInt(userId), officialId);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();

                    if ("success".equals(apiResponse.getStatus())) {
                        Toast.makeText(onlineappointmentt.this, "Appointment request submitted successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Close activity on success
                    } else {
                        Toast.makeText(onlineappointmentt.this, "Failed to submit the appointment request", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(onlineappointmentt.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(onlineappointmentt.this, "Network error, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
