package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barngyapp.R;

public class servicess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.services);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView onlineAppointmentTextView = findViewById(R.id.textView5);
        onlineAppointmentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ServicessActivity", "Online Appointment");
                Intent intent = new Intent(servicess.this, onlineappointmentt.class);
                startActivity(intent);
            }
        });

        TextView onlineApplicationTextView = findViewById(R.id.textView4);
        onlineApplicationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ServicessActivity", "Online Application");
                Intent intent = new Intent(servicess.this, onlineapplicationn.class);
                startActivity(intent);
            }
        });

    }
}