package com.example.barngyapp.adminapplicationAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barngyapp.R;
import com.example.barngyapp.backends.onlineapplicationn;
import com.example.barngyapp.backends.onlineappointmentt;

public class adminservices extends AppCompatActivity { // Extend AppCompatActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_services);

        // Adjust padding for system insets if needed
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView onlineAppointmentTextView = findViewById(R.id.Online_Appointment);
        onlineAppointmentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("adminservices", "Online Appointment");
                Intent intent = new Intent(adminservices.this, onlineappointmentt.class);
                startActivity(intent);
            }
        });

        TextView onlineApplicationTextView = findViewById(R.id.Online_Application);
        onlineApplicationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("adminservices", "Online Application");
                Intent intent = new Intent(adminservices.this, AdminApplicationMain.class);
                startActivity(intent);
            }
        });
    }
}
