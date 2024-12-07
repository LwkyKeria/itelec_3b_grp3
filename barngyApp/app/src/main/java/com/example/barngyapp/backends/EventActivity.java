package com.example.barngyapp.backends;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barngyapp.R;
import com.example.barngyapp.eventadapter.EventAdapter;
import com.example.barngyapp.eventadapter.Event;
import com.example.barngyapp.backendapi.ApiClient;
import com.example.barngyapp.backendapi.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {
    private RecyclerView eventsRecyclerView;
    private EventAdapter eventAdapter;
    private ApiService apiService;
    private ProgressBar progressBar;
    private TextView noEventsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        noEventsText = findViewById(R.id.noEventsText);
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);

        // Setup RecyclerView
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventAdapter = new EventAdapter(new ArrayList<>());
        eventsRecyclerView.setAdapter(eventAdapter);

        // Initialize API service
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        // Fetch events
        loadEvents();
    }

    private void loadEvents() {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<Event>> call = apiService.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Event> events = response.body();
                    if (events.isEmpty()) {
                        noEventsText.setVisibility(View.VISIBLE);
                        eventsRecyclerView.setVisibility(View.GONE);
                    } else {
                        noEventsText.setVisibility(View.GONE);
                        eventsRecyclerView.setVisibility(View.VISIBLE);
                        eventAdapter.updateEvents(events);
                    }
                } else {
                    showError("Error loading events");
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showError("Network error: " + t.getMessage());
            }
        });
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        noEventsText.setText(R.string.error_loading_events);
        noEventsText.setVisibility(View.VISIBLE);
        eventsRecyclerView.setVisibility(View.GONE);
    }
}