package com.example.barngyapp.adminapplicationAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminApplicationMain extends AppCompatActivity {

    private Button requestDocumentButton;
    private TextView documentStatusTextView;
    private ProgressBar loadingSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_application);

        // Initialize views
        requestDocumentButton = findViewById(R.id.requestDocumentButton);
        documentStatusTextView = findViewById(R.id.documentStatusTextView);
        loadingSpinner = findViewById(R.id.loadingSpinner);

        // Ensure views are properly initialized
        if (requestDocumentButton == null || documentStatusTextView == null || loadingSpinner == null) {
            Log.e("AdminApplicationMain", "Some views are not initialized correctly.");
            return;
        }

        // Button click listener to request a document
        requestDocumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDocument();  // Handle document request
            }
        });
    }

    private void requestDocument() {
        // Show loading spinner while processing
        loadingSpinner.setVisibility(View.VISIBLE);
        documentStatusTextView.setText(""); // Clear previous status

        // Use ExecutorService for background task (instead of AsyncTask)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Simulate network delay (Replace with actual network request)
                try {
                    Thread.sleep(2000);  // Simulating a 2-second network request
                    // Simulate a successful document request (replace with real logic)
                    final boolean success = true;

                    // Run on the UI thread after the background task completes
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Hide loading spinner once task is done
                            loadingSpinner.setVisibility(View.GONE);

                            // Update UI with success or failure message
                            if (success) {
                                documentStatusTextView.setText("Document requested successfully.");
                            } else {
                                documentStatusTextView.setText("Failed to request document.");
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    // Handle failure on UI thread if error occurs
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingSpinner.setVisibility(View.GONE);
                            documentStatusTextView.setText("Error requesting document.");
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Optionally clean up resources if needed
    }
}
