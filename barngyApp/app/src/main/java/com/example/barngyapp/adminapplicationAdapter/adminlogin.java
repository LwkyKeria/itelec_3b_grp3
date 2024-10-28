package com.example.barngyapp.adminapplicationAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;

public class adminlogin extends AppCompatActivity {

    private EditText etAdminUsername, etAdminPassword;

    // Hardcoded admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    ImageView ShowPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        etAdminUsername = findViewById(R.id.etAdminUsername);
        etAdminPassword = findViewById(R.id.etAdminPassword);
        ShowPassword = findViewById(R.id.ivShowPassword);

        Button btnAdminLogin = findViewById(R.id.btnAdminLogin);
        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etAdminUsername.getText().toString().trim();
                String password = etAdminPassword.getText().toString().trim();

                if (validateLogin(username, password)) {
                    // Successful login, navigate to admin home page
                    Intent intent = new Intent(adminlogin.this, adminHomePage.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Show error message using Toast
                    Toast.makeText(adminlogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    etAdminPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ShowPassword.setImageResource(R.drawable.eyeepas); // Closed eye icon
                } else {
                    // Show password
                    etAdminPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ShowPassword.setImageResource(R.drawable.view); // Open eye icon
                }
                isPasswordVisible = !isPasswordVisible;

                // Move the cursor to the end of the text
                etAdminPassword.setSelection(etAdminPassword.getText().length());
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }
}
