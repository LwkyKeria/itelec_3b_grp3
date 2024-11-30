package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barngyapp.R;

public class Status extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Online Appointment TextView
        TextView onlineAppointmentTextView = findViewById(R.id.viewAppoint);
        onlineAppointmentTextView.setOnClickListener(v -> {
            Log.d("StatusActivity", "View Status of Appointment");
            Intent intent = new Intent(Status.this, viewappointment.class);
            startActivity(intent);
        });

        // Online Application TextView
        TextView onlineApplicationTextView = findViewById(R.id.viewApp);
        onlineApplicationTextView.setOnClickListener(v -> {
            Log.d("StatusActivity", "View Status of Application");
            Intent intent = new Intent(Status.this, viewapplication.class);
            startActivity(intent);
        });

        // Online Application ImageView (appointment)
        ImageView onlineApplicationImageView = findViewById(R.id.appointment);
        onlineApplicationImageView.setOnClickListener(v -> {
            Log.d("StatusActivity", "Online Application via Appointment");
            Intent intent = new Intent(Status.this, viewappointment.class);
            startActivity(intent);
        });

        // Online Appointment ImageView (application)
        ImageView onlineAppointmentImageView = findViewById(R.id.application);
        onlineAppointmentImageView.setOnClickListener(v -> {
            Log.d("StatusActivity", "Online Appointment via Application");
            Intent intent = new Intent(Status.this, viewapplication.class);
            startActivity(intent);
        });
    }
}
