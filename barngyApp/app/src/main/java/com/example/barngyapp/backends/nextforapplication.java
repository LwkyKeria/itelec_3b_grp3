package com.example.barngyapp.backends;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.ApiResponse;
import com.example.barngyapp.backendapi.ApiService;
import com.example.barngyapp.backendapi.DocumentRequest;
import com.example.barngyapp.backendapi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class nextforapplication extends AppCompatActivity {

    private EditText edtReason;
    private List<String> selectedDocuments;
    private TextView reqDocsTextView;
    private TextView userIdTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextforapplication);

        edtReason = findViewById(R.id.editTextText);
        Button btnSendRequest = findViewById(R.id.btnRequest);
        reqDocsTextView = findViewById(R.id.reqDocs);
        userIdTextView = findViewById(R.id.textViewUserId);

        // Retrieve selected documents passed from the previous activity
        selectedDocuments = getIntent().getStringArrayListExtra("selectedDocuments");

        if (selectedDocuments != null && !selectedDocuments.isEmpty()) {
            StringBuilder documentsList = new StringBuilder("Selected Documents:\n");
            for (String doc : selectedDocuments) {
                documentsList.append(doc).append("\n");
            }
            reqDocsTextView.setText(documentsList.toString());
        } else {
            reqDocsTextView.setText("No documents selected.");
        }

        // Retrieve the user ID from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("user_id", null); // Using null to check if not available

        if (userIdString == null) {
            userIdTextView.setText("User ID: Not Available");
            Toast.makeText(this, "User is not logged in. Please log in first.", Toast.LENGTH_SHORT).show();
        } else {
            try {
                // Convert userId to Integer
                int userId = Integer.parseInt(userIdString);
                userIdTextView.setText("User ID: " + userId);

                // Handle the request submission when the button is clicked
                btnSendRequest.setOnClickListener(v -> {
                    String reason = edtReason.getText().toString();
                    if (!reason.isEmpty() && selectedDocuments != null && !selectedDocuments.isEmpty()) {
                        sendDocumentRequest(userId, selectedDocuments, reason);
                    } else {
                        Toast.makeText(nextforapplication.this, "Please provide a reason and select documents.", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (NumberFormatException e) {
                userIdTextView.setText("Invalid User ID");
                Log.e("DocumentRequest", "Invalid user ID format: " + userIdString);
            }
        }
    }

    private void sendDocumentRequest(int userId, List<String> selectedDocuments, String reason) {
        List<Integer> documentIds = new ArrayList<>();
        for (String docName : selectedDocuments) {
            int documentId = getDocumentIdByName(docName);
            if (documentId != -1) {
                documentIds.add(documentId);
            }
        }

        if (documentIds.isEmpty()) {
            Toast.makeText(this, "No valid documents selected.", Toast.LENGTH_SHORT).show();
            return;
        }

        DocumentRequest request = new DocumentRequest(userId, documentIds, reason);

        ApiService apiService = RetrofitClient.getApiService();
        apiService.sendDocumentRequest(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.isSuccess()) {
                        Toast.makeText(nextforapplication.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // Optionally, navigate back or clear the form
                    } else {
                        Toast.makeText(nextforapplication.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("DocumentRequest", "Failed response: " + response.message());
                    Toast.makeText(nextforapplication.this, "Request failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("DocumentRequest", "Request failed", t);
                Toast.makeText(nextforapplication.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private int getDocumentIdByName(String docName) {
        switch (docName) {
            case "Barangay ID": return 1;
            case "Barangay Clearance": return 2;
            case "Barangay Certificate": return 3;
            case "Barangay Certificate of Residency": return 4;
            case "Barangay Indigency": return 5;
            case "Barangay Protection Order": return 6;
            case "Barangay Business Clearance": return 7;
            case "Barangay Blotter Report": return 8;
            case "Barangay Certificate to File Action": return 9;
            case "Barangay Permit for Events": return 10;
            case "Barangay Voters Registration Certificate": return 11;
            case "Barangay Community Tax Certificate": return 12;
            default: return -1; // Unknown document
        }
    }
}
