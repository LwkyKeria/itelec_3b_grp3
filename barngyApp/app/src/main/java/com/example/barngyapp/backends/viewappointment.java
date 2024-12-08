package com.example.barngyapp.backends;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.Appointment;
import com.example.barngyapp.backendapi.AppointmentResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.ArrayList;
import java.util.List;

public class viewappointment extends AppCompatActivity {
    private RecyclerView appointmentRecyclerView;
    private ProgressBar loadingProgressBar;
    private TextView emptyStateText;
    private com.example.barngyapp.backends.AppointmentAdapter appointmentAdapter;
    private ApiService apiService;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SharedPreferences sharedPreferences;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_view_appointment);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getString("user_id", null);

        if (userId == null) {
            Toast.makeText(this, "Please log in first", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        initializeViews();
        setupRecyclerView();
        setupSwipeRefresh();
        initializeRetrofit();
        loadAppointments();
    }

    private void initializeViews() {
        appointmentRecyclerView = findViewById(R.id.appointmentRecyclerView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        emptyStateText = findViewById(R.id.emptyStateText);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    private void setupRecyclerView() {
        appointmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new com.example.barngyapp.backends.AppointmentAdapter(new ArrayList<>());
        appointmentRecyclerView.setAdapter(appointmentAdapter);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this::loadAppointments);
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://barangayapp.x10.mx/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void loadAppointments() {
        showLoading();

        apiService.getUserAppointments(userId).enqueue(new Callback<AppointmentResponse>() {
            @Override
            public void onResponse(Call<AppointmentResponse> call, Response<AppointmentResponse> response) {
                hideLoading();
                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    AppointmentResponse appointmentResponse = response.body();
                    if ("success".equals(appointmentResponse.getStatus())) {
                        List<Appointment> appointments = appointmentResponse.getData();
                        if (appointments != null && !appointments.isEmpty()) {
                            appointmentAdapter.setAppointments(appointments);
                            showAppointments();
                        } else {
                            showEmptyState();
                        }
                    } else {
                        showError("Failed to load appointments");
                    }
                } else {
                    showError("Server error occurred");
                }
            }

            @Override
            public void onFailure(Call<AppointmentResponse> call, Throwable t) {
                hideLoading();
                swipeRefreshLayout.setRefreshing(false);
                showError("Network error: " + t.getMessage());
            }
        });
    }

    private void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        appointmentRecyclerView.setVisibility(View.GONE);
        emptyStateText.setVisibility(View.GONE);
    }

    private void hideLoading() {
        loadingProgressBar.setVisibility(View.GONE);
    }

    private void showAppointments() {
        appointmentRecyclerView.setVisibility(View.VISIBLE);
        emptyStateText.setVisibility(View.GONE);
    }

    private void showEmptyState() {
        appointmentRecyclerView.setVisibility(View.GONE);
        emptyStateText.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (appointmentAdapter.getItemCount() == 0) {
            showEmptyState();
        }
    }
}