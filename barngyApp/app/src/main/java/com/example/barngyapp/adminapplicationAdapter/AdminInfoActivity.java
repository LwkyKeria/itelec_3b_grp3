package com.example.barngyapp.adminapplicationAdapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
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

public class AdminInfoActivity extends AppCompatActivity {

    private EditText etOfficeAddress, etPhoneNumber, etEmail;
    private Button btnSubmitInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_info);

        // Initialize views
        etOfficeAddress = findViewById(R.id.etOfficeAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);
        btnSubmitInfo = findViewById(R.id.btnSubmitInfo);

        // Handle button click
        btnSubmitInfo.setOnClickListener(v -> {
            String address = etOfficeAddress.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            // Validate input fields
            if (!isValidInput(address, phone, email)) return;

            // Submit data to server
            submitBarangayInfo(address, phone, email);
        });
    }

    private boolean isValidInput(String address, String phone, String email) {
        // Validate all input fields
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Office Address is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Phone Number is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void submitBarangayInfo(String address, String phone, String email) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Create a HashMap for the POST data
        HashMap<String, String> barangayInfo = new HashMap<>();
        barangayInfo.put("address", address);
        barangayInfo.put("phone", phone);
        barangayInfo.put("email", email);

        // Call the API
        Call<ApiResponse> call = apiService.createBarangayInfo(barangayInfo);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        Toast.makeText(AdminInfoActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("AdminInfoActivity", "Success: " + apiResponse.getMessage());
                        clearInputFields(); // Clear input fields on success
                    } else {
                        Toast.makeText(AdminInfoActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("AdminInfoActivity", "Failed: " + apiResponse.getMessage());
                    }
                } else {
                    Log.e("AdminInfoActivity", "Failed: " + response.code());
                    Toast.makeText(AdminInfoActivity.this, "Submission failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("AdminInfoActivity", "Error: " + t.getMessage());
                Toast.makeText(AdminInfoActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearInputFields() {
        etOfficeAddress.setText("");
        etPhoneNumber.setText("");
        etEmail.setText("");
    }
}
