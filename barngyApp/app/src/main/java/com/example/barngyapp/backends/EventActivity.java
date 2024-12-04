package com.example.barngyapp.backends;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;
import com.example.barngyapp.eventadapter.EventAdapter;
import com.example.barngyapp.eventadapter.Event;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private RecyclerView eventsRecyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event); // Make sure this layout exists

        // Initialize the RecyclerView and Adapter
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create sample data for events
        eventList = new ArrayList<>();
        eventList.add(new Event("Event 1", "Description of event 1", "Oct 10, 2024", "Location 1"));
        eventList.add(new Event("Event 2", "Description of event 2", "Oct 11, 2024", "Location 2"));
        eventList.add(new Event("Event 3", "Description of event 3", "Oct 12, 2024", "Location 3"));

        // Set the adapter for RecyclerView
        eventAdapter = new EventAdapter(eventList);
        eventsRecyclerView.setAdapter(eventAdapter);
    }
}
