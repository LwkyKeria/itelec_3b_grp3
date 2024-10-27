package com.example.barngyapp.eventadapter;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;

import java.util.ArrayList;
import java.util.List;

public class eventt extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;
    private CalendarView calendarView;
    private TextView textEventLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data to populate the RecyclerView
        eventList = new ArrayList<>();
        eventList.add(new Event("Music Concert", "2024-11-10", "Concert Hall"));
        eventList.add(new Event("Art Exhibition", "2024-11-15", "City Art Gallery"));
        eventList.add(new Event("Food Festival", "2024-11-20", "Town Square"));
        eventList.add(new Event("Tech Conference", "2024-11-25", "Convention Center"));
        eventList.add(new Event("Sports Meet", "2024-12-01", "Sports Arena"));

        eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);

        // Initialize CalendarView and TextView
        calendarView = findViewById(R.id.calendar_view);
        textEventLocation = findViewById(R.id.text_event_location);

        // Set up CalendarView date change listener
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            String location = getLocationForDate(selectedDate);
            textEventLocation.setText("Event Location: " + (location != null ? location : "No event"));
        });
    }

    // Method to get event location based on the selected date
    private String getLocationForDate(String date) {
        for (Event event : eventList) {
            if (event.getDate().equals(date)) {
                return event.getLocation();
            }
        }
        return null; // Return null if no matching event is found
    }
}
