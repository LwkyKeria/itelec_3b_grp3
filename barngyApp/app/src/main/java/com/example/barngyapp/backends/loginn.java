package com.example.barngyapp.backends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.adminapplicationAdapter.adminHomePage;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.RetrofitClient;
import com.example.barngyapp.backendapi.User;
import com.example.barngyapp.backendapi.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginn extends AppCompatActivity {

    EditText etLoginUser, etLoginPass;
    Button btnLogin, btncreate;
    ImageView ShowPassword;
    private boolean isPasswordVisible = false;

    // Hardcoded admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);  // Link to your login layout

        etLoginUser = findViewById(R.id.username);
        etLoginPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btncreate = findViewById(R.id.btnCreate);
        ShowPassword = findViewById(R.id.ivShowPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginUser = etLoginUser.getText().toString().trim();
                String loginPass = etLoginPass.getText().toString().trim();

                if (!loginUser.isEmpty() && !loginPass.isEmpty()) {
                    if (loginUser.equals(ADMIN_USERNAME) && loginPass.equals(ADMIN_PASSWORD)) {
                        // Navigate to Admin Homepage
                        Intent intent = new Intent(loginn.this, adminHomePage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Proceed with user login API
                        loginUser(loginUser, loginPass);
                    }
                } else {
                    Toast.makeText(loginn.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    etLoginPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ShowPassword.setImageResource(R.drawable.eyeepas); // Closed eye icon
                } else {
                    // Show password
                    etLoginPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ShowPassword.setImageResource(R.drawable.view); // Open eye icon
                }
                isPasswordVisible = !isPasswordVisible;

                // Move the cursor to the end of the text
                etLoginPass.setSelection(etLoginPass.getText().length());
            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the 'createe' activity
                Intent intent = new Intent(loginn.this, createe.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String username, String password) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        User user = new User(username, password);

        Call<ApiResponse> call = apiService.loginUser(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                etLoginUser.setText("");
                etLoginPass.setText("");

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        // Assuming 'user_id' is returned in the response
                        String userId = apiResponse.getUserId();  // Fetch user_id from ApiResponse

                        // Save the user_id in SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_id", userId); // Store user_id
                        editor.apply();

                        // Debug log to confirm user_id is saved
                        Log.d("loginn", "User ID saved: " + userId);

                        Toast.makeText(loginn.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginn.this, homepagee.class); // Navigate to user homepage
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(loginn.this, "Login Failed: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginn.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(loginn.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                etLoginUser.setText("");
                etLoginPass.setText("");
            }
        });
    }


}
