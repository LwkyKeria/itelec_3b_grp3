package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD:barngyApp/app/src/main/java/com/example/barngyapp/backends/createe.java
import com.example.barngyapp.R;

=======
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
>>>>>>> b05622cb549d6e98a88ff031ee3194ed40686fd1:barngyApp/app/src/main/java/com/example/barngyapp/createe.java

public class createe extends AppCompatActivity {

    EditText etUsername, etPassword, etFirstName, etLastName, etMiddleName, etPhone;
    Button btnCreate;
    TextView tvPasswordWarning, tvPhoneWarning;
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
        btnCreate.setOnClickListener(v -> {
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
                    TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) ||
                    TextUtils.isEmpty(middleName) || TextUtils.isEmpty(phone)) {
                Toast.makeText(createe.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 7) {
                tvPasswordWarning.setVisibility(View.VISIBLE); // Show password warning
            } else if (!phone.matches("\\d{11}")) {
                tvPhoneWarning.setVisibility(View.VISIBLE); // Show phone number warning
            } else {
                // Check if username is unique
                checkUsernameUnique(username, password, firstName, lastName, middleName, phone);
            }
        });

        ShowPasswordCreate.setOnClickListener(v -> {
            if (isPasswordVisible) {
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                ShowPasswordCreate.setImageResource(R.drawable.eyeepas);
            } else {
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                ShowPasswordCreate.setImageResource(R.drawable.view);
            }
            isPasswordVisible = !isPasswordVisible;
            etPassword.setSelection(etPassword.getText().length());
        });
    }

    private void checkUsernameUnique(String username, String password, String firstName, String lastName, String middleName, String phone) {
        new Thread(() -> {
            try {
                URL url = new URL("barangayapp.x10.mx/api/routes/check_username.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("username", username);

                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes());
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.getString("status").equals("unique")) {
                    // Username is unique, proceed with registration
                    registerUser(username, password, firstName, lastName, middleName, phone);
                } else {
                    runOnUiThread(() -> Toast.makeText(createe.this, "Username already exists.", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException | JSONException e) {
                runOnUiThread(() -> Toast.makeText(createe.this, "Failed to connect to the server. Please try again.", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void registerUser(String username, String password, String firstName, String lastName, String middleName, String phone) {
        new Thread(() -> {
            try {
                URL url = new URL("barangayapp.x10.mx/api/routes/insert_data.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("username", username);
                jsonParam.put("password", password);
                jsonParam.put("firstName", firstName);
                jsonParam.put("lastName", lastName);
                jsonParam.put("middleName", middleName);
                jsonParam.put("phone", phone);

                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes());
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                runOnUiThread(() -> {
                    try {
                        if (jsonResponse.getString("message").equals("User was successfully registered.")) {
                            Toast.makeText(createe.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(createe.this, loginn.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(createe.this, "Registration failed: " + jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (IOException | JSONException e) {
                runOnUiThread(() -> Toast.makeText(createe.this, "Failed to connect to the server. Please try again.", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
