package com.example.barangayappv1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class home extends AppCompatActivity {


    TextView fullnameTextView;
    dblogin mydb;


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Initialize database and TextView
        fullnameTextView = findViewById(R.id.tvfname);
        mydb = new dblogin(this);


        // Get the username and number passed from the previous activity
        String fullname = getIntent().getStringExtra("fullname");
        String number = getIntent().getStringExtra("number");


        // Fetch user data based on the fullname and number
        Cursor cursor = mydb.getUserData(fullname, number);


        // Check if cursor contains data
        if (cursor != null && cursor.moveToFirst()) {
            // Get the name from the cursor
            fullname = cursor.getString(cursor.getColumnIndex("name"));


            // Set the full name in the TextView
            fullnameTextView.setText(fullname);
        }


        // Close the cursor
        if (cursor != null) {
            cursor.close();
        }


        // Apply window insets listener for edge-to-edge layout support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void launchSettings(View v) {
        Intent i = new Intent(this, profile.class);
        startActivity(i);
    }
}
