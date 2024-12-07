package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.ApiResponse;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.RetrofitClient;
import com.example.barngyapp.backendapi.registerUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createe extends AppCompatActivity {

    private EditText etUsername, etPassword, etFirstName, etLastName, etMiddleName, etPhone;
    private Button btnCreate;
    private TextView tvPasswordWarning, tvPhoneWarning;
    private ImageView ShowPasswordCreate;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        etUsername = findViewById(R.id.etusername);
        etPassword = findViewById(R.id.etpassword);
        etFirstName = findViewById(R.id.etFname);
        etLastName = findViewById(R.id.etLname);
        etMiddleName = findViewById(R.id.etMname);
        etPhone = findViewById(R.id.etCpnum);
        btnCreate = findViewById(R.id.btncrt);
        ShowPasswordCreate = findViewById(R.id.ivShowPassword);
        tvPasswordWarning = findViewById(R.id.tvPasswordWarning);
        tvPhoneWarning = findViewById(R.id.tvPhoneWarning);
    }

    private void setupClickListeners() {
        btnCreate.setOnClickListener(v -> validateAndRegister());

        ShowPasswordCreate.setOnClickListener(v -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ShowPasswordCreate.setImageResource(R.drawable.view);
        } else {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ShowPasswordCreate.setImageResource(R.drawable.eyeepas);
        }
        etPassword.setSelection(etPassword.getText().length());
    }

    private void validateAndRegister() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String middleName = etMiddleName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        tvPasswordWarning.setVisibility(View.GONE);
        tvPhoneWarning.setVisibility(View.GONE);

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                TextUtils.isEmpty(middleName) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 7) {
            tvPasswordWarning.setVisibility(View.VISIBLE);
            return;
        }

        if (!phone.matches("\\d{11}")) {
            tvPhoneWarning.setVisibility(View.VISIBLE);
            return;
        }

        registerUser newUser = new registerUser(username, password, firstName, lastName, middleName, phone);
        registerUser(newUser);
    }

    private void registerUser(registerUser user) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ApiResponse> call = apiService.createUser(user);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API Response", "Response body: " + response.body().toString());
                    if (response.body().isSuccess()) {
                        Toast.makeText(createe.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(createe.this, loginn.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(createe.this, "Registration failed: " +
                                response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("API Response", "Response failed with message: " + response.message());
                    Toast.makeText(createe.this, "Registration failed: " +
                            response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(createe.this,
                        "Failed to connect to the server. Please try again.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}