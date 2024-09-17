package com.example.barangayappv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signup extends AppCompatActivity {

    EditText user, pass, num, name;
    Button btn;
    dblogin datab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupxml);

        // Initialize UI elements
        num = findViewById(R.id.signnum);
        name = findViewById(R.id.signname);
        user = findViewById(R.id.signun);
        pass = findViewById(R.id.signpw);
        btn = findViewById(R.id.btnsign);
        datab = new dblogin(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = name.getText().toString().trim();
                String number = num.getText().toString().trim();
                String username = user.getText().toString().trim();
                String userpassword = pass.getText().toString().trim();

                // Validate input fields
                if (username.isEmpty() || userpassword.isEmpty() || fullname.isEmpty() || number.isEmpty()) {
                    Toast.makeText(signup.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the user exists
                    Boolean checkFnNbUnUp = datab.checkFullnameNumberUsernamePassword(fullname, number, username, userpassword);
                    Log.d("SignupActivity", "Check for existing user: " + checkFnNbUnUp);

                    if (!checkFnNbUnUp) {
                        // User does not exist, proceed with insertion
                        Boolean insert = datab.insertData(fullname, number, username, userpassword);
                        if (insert) {
                            Toast.makeText(signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Log.d("SignupActivity", "Registration successful, navigating to home");
                            navigateToHome();
                        } else {
                            Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            Log.d("SignupActivity", "Registration failed");
                        }
                    } else {
                        // User already exists
                        Toast.makeText(signup.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                    }
                }

                // Apply window insets listener for edge-to-edge layout support
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear activity stack
        startActivity(intent);
    }
}
