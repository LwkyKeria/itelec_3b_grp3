package com.example.barngyapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class createe extends AppCompatActivity {

    EditText etUsername, etPassword, etFirstName, etLastName, etMiddleName, etPhone;
    Button btnCreate;
    TextView tvPasswordWarning, tvPhoneWarning; // For warnings
    ImageView ShowPasswordCreate;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        // Initialize UI elements
        etUsername = findViewById(R.id.etusername);
        etPassword = findViewById(R.id.etpassword);
        etFirstName = findViewById(R.id.etFname);
        etLastName = findViewById(R.id.etLname);
        etMiddleName = findViewById(R.id.etMname);
        etPhone = findViewById(R.id.etCpnum);
        btnCreate = findViewById(R.id.btncrt);
        ShowPasswordCreate = findViewById(R.id.ivShowPassword);


        // Initialize warning text views
        tvPasswordWarning = findViewById(R.id.tvPasswordWarning);
        tvPhoneWarning = findViewById(R.id.tvPhoneWarning);

        // Set up the Create button click listener
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input from the user
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String middleName = etMiddleName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();

                // Reset warnings to invisible
                tvPasswordWarning.setVisibility(View.GONE);
                tvPhoneWarning.setVisibility(View.GONE);

                // Basic validation
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(middleName) || TextUtils.isEmpty(phone)) {
                    Toast.makeText(createe.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 7) {
                    tvPasswordWarning.setVisibility(View.VISIBLE); // Show password warning
                } else if (!phone.matches("\\d{11}")) {
                    tvPhoneWarning.setVisibility(View.VISIBLE); // Show phone number warning
                } else {
                    // If validation is successful, show success message
                    Toast.makeText(createe.this, "Sign up successful!", Toast.LENGTH_SHORT).show();

                    // Move to Login Activity
                    Intent intent = new Intent(createe.this, loginn.class);
                    intent.putExtra("username", username);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    finish();
                }
            }
        });

        ShowPasswordCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ShowPasswordCreate.setImageResource(R.drawable.eyeepas); // Closed eye icon
                } else {
                    // Show password
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ShowPasswordCreate.setImageResource(R.drawable.view); // Open eye icon
                }
                isPasswordVisible = !isPasswordVisible;

                // Move the cursor to the end of the text
                etPassword.setSelection(etPassword.getText().length());
            }
        });


    }
}
