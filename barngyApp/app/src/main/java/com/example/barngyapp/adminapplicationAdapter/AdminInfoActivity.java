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

        btnSubmitInfo.setOnClickListener(v -> {
            String address = etOfficeAddress.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            submitBarangayInfo(address, phone, email);
        });
    }

    private void submitBarangayInfo(String address, String phone, String email) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        HashMap<String, String> barangayInfo = new HashMap<>();
        barangayInfo.put("address", address);
        barangayInfo.put("phone", phone);
        barangayInfo.put("email", email);

        Call<ApiResponse> call = apiService.createBarangayInfo(barangayInfo);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AdminInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("AdminInfoActivity", "Success: " + response.body().getMessage());
                } else {
                    Log.e("AdminInfoActivity", "Failed: " + response.code());
                    Toast.makeText(AdminInfoActivity.this, "Submission failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("AdminInfoActivity", "Error: " + t.getMessage());
                Toast.makeText(AdminInfoActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
