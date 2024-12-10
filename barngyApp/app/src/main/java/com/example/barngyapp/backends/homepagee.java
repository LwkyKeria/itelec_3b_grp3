package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;
import com.example.barngyapp.announcementadapter.AnnouncementAdapter;
import com.example.barngyapp.announcementadapter.announcement;

import java.util.ArrayList;
import java.util.List;

public class homepagee extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnnouncementAdapter adapter;
    private List<announcement> announcementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage); // Ensure this layout exists

        // Initialize the RecyclerView and the adapter
         // Make sure this ID exists

        // Apply system bars insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setting up click listeners
        setupClickListeners();
    }

    private void setupClickListeners() {
        // Ensure the IDs are correct in your layout file
        RelativeLayout rlservice = findViewById(R.id.service);
        rlservice.setOnClickListener(v -> {
            Toast.makeText(homepagee.this, "Services", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(homepagee.this, servicess.class); // Ensure Services.class exists
            startActivity(intent);
        });

        RelativeLayout rlprofile = findViewById(R.id.profileR);
        rlprofile.setOnClickListener(v -> {
            Intent intent = new Intent(homepagee.this, profilee.class); // Ensure Profilee.class exists
            startActivity(intent);
        });


        RelativeLayout rlevent = findViewById(R.id.event);  // Ensure this ID matches the one in your XML layout
        rlevent.setOnClickListener(v -> {
            // Trigger the event-related action here
            // For example, displaying a Toast message or triggering a different activity

            Toast.makeText(homepagee.this, "Event Button Clicked!", Toast.LENGTH_SHORT).show();

            // Optionally, start a new activity related to the event
            Intent intent = new Intent(homepagee.this, EventActivity.class);  // Ensure EventActivity.class exists
            startActivity(intent);
        });


        RelativeLayout rlstatus = findViewById(R.id.statusR); // Fix the reference for statusR
        rlstatus.setOnClickListener(v -> {
            Intent intent = new Intent(homepagee.this, Status.class); // Ensure Status.class exists
            startActivity(intent);
        });
    }
}
