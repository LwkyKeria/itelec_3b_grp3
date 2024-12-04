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

import java.io.IOException;
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
        String userId = sharedPreferences.getString("user_id", "Not Available");

        if ("Not Available".equals(userId)) {
            userIdTextView.setText("User ID: Not Available");
            Toast.makeText(this, "User is not logged in. Please log in first.", Toast.LENGTH_SHORT).show();
        } else {
            userIdTextView.setText("User ID: " + userId);
        }

        // Handle the request submission when the button is clicked
        btnSendRequest.setOnClickListener(v -> {
            String reason = edtReason.getText().toString();
            if (!reason.isEmpty() && selectedDocuments != null && !selectedDocuments.isEmpty()) {
                sendDocumentRequest(userId, selectedDocuments, reason);
            } else {
                Toast.makeText(nextforapplication.this, "Please provide a reason and select documents.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendDocumentRequest(String userId, List<String> documentIds, String reason) {
        // Create a new DocumentRequest object with the provided data
        DocumentRequest request = new DocumentRequest(userId, documentIds, reason);

        // Make the API call to send the request to the PHP backend
        ApiService apiService = RetrofitClient.getApiService();
        apiService.sendDocumentRequest(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(nextforapplication.this, "Request sent successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Log more detailed error info
                    Log.d("DocumentRequest", "Error Response: " + response.code());
                    Log.d("DocumentRequest", "Error Message: " + response.message());

                    // Log the response body for further details
                    if (response.errorBody() != null) {
                        try {
                            String errorResponse = response.errorBody().string();
                            Log.d("DocumentRequest", "Error Body: " + errorResponse);
                        } catch (IOException e) {
                            Log.e("DocumentRequest", "Error reading response body", e);
                        }
                    }

                    Toast.makeText(nextforapplication.this, "Failed to send request", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("DocumentRequest", "Request failed", t);
                Toast.makeText(nextforapplication.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
