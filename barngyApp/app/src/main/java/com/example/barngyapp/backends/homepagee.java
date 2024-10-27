package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;
import com.example.barngyapp.announcementadapter.AnnouncementAdapter;
import com.example.barngyapp.announcementadapter.announcement;
import com.example.barngyapp.eventadapter.eventt;

import java.util.ArrayList;
import java.util.List;

public class homepagee extends AppCompatActivity { // Class name updated to start with uppercase

    private RecyclerView recyclerView;
    private AnnouncementAdapter adapter;
    private List<announcement> announcementList; // Change announcement to Announcement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.homepage); // Ensure this layout exists

        recyclerView = findViewById(R.id.recyclerView); // Make sure this ID exists
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the announcement list
        announcementList = new ArrayList<>();
        announcementList.add(new announcement("Event 1", "Description of event 1", "Oct 10, 2024"));
        announcementList.add(new announcement("Event 2", "Description of event 2", "Oct 11, 2024"));
        announcementList.add(new announcement("Event 3", "Description of event 3", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 4", "Description of event 4", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 5", "Description of event 5", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 6", "Description of event 6", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 7", "Description of event 7", "Oct 12, 2024"));

        adapter = new AnnouncementAdapter(announcementList);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setting up click listeners
        setupClickListeners();
    }

    private void setupClickListeners() {
        RelativeLayout rlservice = findViewById(R.id.service); // Ensure these IDs exist
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

        RelativeLayout rlinfo = findViewById(R.id.infobr);
        rlinfo.setOnClickListener(v -> {
            Intent intent = new Intent(homepagee.this, brgyInfo.class); // Ensure BrgyInfo.class exists
            startActivity(intent);
        });

        RelativeLayout rlevent = findViewById(R.id.event);
        rlevent.setOnClickListener(v -> {
            Intent intent = new Intent(homepagee.this, eventt.class); // Ensure Eventt.class exists
            startActivity(intent);
        });
    }
}
