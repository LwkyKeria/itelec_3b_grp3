package com.example.barngyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class homepagee extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnnouncementAdapter adapter;
    private List<announcement> announcementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.homepage);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the announcement list
        announcementList = new ArrayList<>();
        announcementList.add(new announcement("Event 1", "Description of event 1", "Oct 10, 2024"));
        announcementList.add(new announcement("Event 2", "Description of event 2", "Oct 11, 2024"));
        announcementList.add(new announcement("Event 3", "Description of event 3", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 4", "Description of event 3", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 5", "Description of event 3", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 6", "Description of event 3", "Oct 12, 2024"));
        announcementList.add(new announcement("Event 7", "Description of event 3", "Oct 12, 2024"));

        adapter = new AnnouncementAdapter(announcementList);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RelativeLayout rlservice = findViewById(R.id.service);
        rlservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homepagee.this, "Services", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(homepagee.this, servicess.class);
                startActivity(intent);
            }
        });

        RelativeLayout rlprofile = findViewById(R.id.profileR);
        rlprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepagee.this, profilee.class);
                startActivity(intent);
            }
        });

        RelativeLayout rlinfo = findViewById(R.id.infobr);
        rlinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepagee.this, brgyInfo.class);
                startActivity(intent);
            }
        });
    }
}