package com.example.barngyapp.eventadapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class eventt extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvNoEvents, textEventLocation;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView_events);
        tvNoEvents = findViewById(R.id.tvNoEvents);
        textEventLocation = findViewById(R.id.text_event_location);
        calendarView = findViewById(R.id.calendar_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch and display events
        fetchEvents();

        // Handle calendar date selection
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            textEventLocation.setText("Selected Date: " + selectedDate);
        });
    }

    private void fetchEvents() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Call<List<Event>> call = apiService.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Event> events = response.body();
                    for (Event event : events) {
                        Log.d("fetchEvents", "Title: " + event.getTitle() +
                                ", Location: " + event.getLocation() +
                                ", Date: " + event.getDate());
                    }
                    // Optionally, bind this list to a RecyclerView adapter
                } else {
                    Log.e("fetchEvents", "Failed to fetch events. Response: " + response.errorBody());
                    Toast.makeText(eventt.this, "Failed to fetch events.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("fetchEvents", "Error: " + t.getMessage(), t);
                Toast.makeText(eventt.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
