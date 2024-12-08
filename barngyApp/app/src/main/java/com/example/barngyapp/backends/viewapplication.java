package com.example.barngyapp.backends;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.barngyapp.R;
import com.example.barngyapp.applicationadapter.Application;
import com.example.barngyapp.applicationadapter.ApplicationAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class viewapplication extends AppCompatActivity {
    private ListView listViewApplications;
    private TextView emptyStateText;
    private ProgressBar progressBar;
    private ApplicationAdapter adapter;
    private List<Application> applicationList;
    private SharedPreferences sharedPreferences;

    private static final String BASE_URL = "https://barangayapp.x10.mx/api/routes/";
    private static final String VIEW_REQUESTS_URL = BASE_URL + "android_viewrequests.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_view_application);

        // Initialize UI components
        listViewApplications = findViewById(R.id.listViewApplications);
        emptyStateText = findViewById(R.id.emptyStateText);
        progressBar = findViewById(R.id.progressBar);

        applicationList = new ArrayList<>();
        adapter = new ApplicationAdapter(this, applicationList);
        listViewApplications.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", null);

        if (userId == null || userId.isEmpty()) {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            fetchApplications(userId);
        }
    }

    private void fetchApplications(String userId) {
        progressBar.setVisibility(View.VISIBLE);
        listViewApplications.setVisibility(View.GONE);
        emptyStateText.setVisibility(View.GONE);

        Log.d("ViewApplication", "Fetching applications for user ID: " + userId);

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("user_id", userId);
        } catch (JSONException e) {
            Log.e("ViewApplication", "JSON creation error: " + e.getMessage(), e);
            showEmptyState("Error creating request.");
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, VIEW_REQUESTS_URL, jsonBody,
                response -> {
                    progressBar.setVisibility(View.GONE);
                    try {
                        if (response.getBoolean("success")) {
                            JSONArray applications = response.getJSONArray("applications");
                            applicationList.clear();

                            for (int i = 0; i < applications.length(); i++) {
                                JSONObject obj = applications.getJSONObject(i);
                                Application application = new Application(
                                        obj.optInt("id", -1),
                                        obj.optString("reason", "No reason provided"),
                                        obj.optString("date", "Unknown date"),
                                        obj.optString("status", "Pending"),
                                        obj.optString("document_title", "No document title")
                                );
                                applicationList.add(application);
                            }

                            if (applicationList.isEmpty()) {
                                showEmptyState("No applications found.");
                            } else {
                                listViewApplications.setVisibility(View.VISIBLE);
                                emptyStateText.setVisibility(View.GONE);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String message = response.optString("message", "No applications found.");
                            showEmptyState(message);
                        }
                    } catch (JSONException e) {
                        Log.e("ViewApplication", "JSON parsing error: " + e.getMessage(), e);
                        showEmptyState("Error parsing response.");
                    }
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    String errorMessage = "Error fetching applications.";
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        try {
                            String responseBody = new String(error.networkResponse.data, "UTF-8");
                            JSONObject data = new JSONObject(responseBody);
                            errorMessage = data.optString("message", "Unknown error occurred.");
                        } catch (Exception e) {
                            Log.e("ViewApplication", "Error reading error response: " + e.getMessage(), e);
                        }
                    }
                    Log.e("ViewApplication", "Volley error: " + error.getMessage(), error);
                    showEmptyState(errorMessage);
                });

        Volley.newRequestQueue(this).add(request);
    }


    private void showEmptyState(String message) {
        progressBar.setVisibility(View.GONE);
        listViewApplications.setVisibility(View.GONE);

        emptyStateText.setText(message);
        emptyStateText.setVisibility(View.VISIBLE);

        // Show Retry Button for errors
        Button retryButton = findViewById(R.id.retryButton);
        retryButton.setVisibility(View.VISIBLE);
        retryButton.setOnClickListener(v -> {
            String userId = sharedPreferences.getString("user_id", null);
            if (userId != null && !userId.isEmpty()) {
                retryButton.setVisibility(View.GONE);  // Hide Retry Button during retry
                fetchApplications(userId);
            }
        });
    }

}
