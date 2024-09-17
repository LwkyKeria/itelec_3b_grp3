package com.example.barangayappv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText un,pw;
    dblogin db;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.loginxml);

        un = findViewById(R.id.etun);
        pw = findViewById(R.id.etpw);
        db = new dblogin(this);
        button = findViewById(R.id.btnlog);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = un.getText().toString();
                String password = pw.getText().toString();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(Login.this, "Fill all the fields", Toast.LENGTH_LONG).show();
            }else{
                boolean checkUnPw = db.checkUsernamePassword(username,password);
                Log.d("LoginActivity", "Username: " + username + " Password: " + password);
                Log.d("LoginActivity", "CheckUserPass result: " + checkUnPw);

                if(checkUnPw){
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "Navigating to Home activity");
                    redirect();
                }else{
                    Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "Login failed");
                }
            }
        }
   });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void launchSettings(View v) {
        Intent i = new Intent(this, signup.class);
        startActivity(i);
    }
    public void redirect() {
        Intent intent = new Intent(this, home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear activity stack
        startActivity(intent);
        finish(); // Close this activity
    }
}