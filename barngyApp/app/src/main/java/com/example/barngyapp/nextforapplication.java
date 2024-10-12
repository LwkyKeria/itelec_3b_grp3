package com.example.barngyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;




public class nextforapplication extends AppCompatActivity {
    private EditText editTextText; // To get the reason from user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextforapplication); // Set your layout

        // To display the requested documents
        TextView reqDocs = findViewById(R.id.reqDocs);
        editTextText = findViewById(R.id.editTextText);
        // Button to request document
        Button btnRequest = findViewById(R.id.btnRequest);

        // Retrieve the Intent that started this Activity
        Intent intent = getIntent();
        boolean isCb1Checked = intent.getBooleanExtra("isCb1Checked", false);
        boolean isCb2Checked = intent.getBooleanExtra("isCb2Checked", false);
        boolean isCb3Checked = intent.getBooleanExtra("isCb3Checked", false);
        boolean isCb4Checked = intent.getBooleanExtra("isCb4Checked", false);
        boolean isCb5Checked = intent.getBooleanExtra("isCb5Checked", false);
        boolean isCb6Checked = intent.getBooleanExtra("isCb6Checked", false);
        boolean isCb7Checked = intent.getBooleanExtra("isCb7Checked", false);
        boolean isCb8Checked = intent.getBooleanExtra("isCb8Checked", false);
        boolean isCb9Checked = intent.getBooleanExtra("isCb9Checked", false);
        boolean isCb10Checked = intent.getBooleanExtra("isCb10Checked", false);
        boolean isCb11Checked = intent.getBooleanExtra("isCb11Checked", false);
        boolean isCb12Checked = intent.getBooleanExtra("isCb12Checked", false);

        // Build a string to display the selected documents
        StringBuilder selectedDocs = new StringBuilder("Selected Documents:\n");
        if (isCb1Checked) selectedDocs.append("Barangay ID\n");
        if (isCb2Checked) selectedDocs.append("Barangay Clearance\n");
        if (isCb3Checked) selectedDocs.append("Barangay Certificate\n");
        if (isCb4Checked) selectedDocs.append("Barangay Certificate for Residency\n");
        if (isCb5Checked) selectedDocs.append("Barangay Indigency\n");
        if (isCb6Checked) selectedDocs.append("Barangay Protection Order\n");
        if (isCb7Checked) selectedDocs.append("Barangay Business Clearance\n");
        if (isCb8Checked) selectedDocs.append("Barangay Blotter Report\n");
        if (isCb9Checked) selectedDocs.append("Barangay Certification to File Action\n");
        if (isCb10Checked) selectedDocs.append("Barangay Permits for Events\n");
        if (isCb11Checked) selectedDocs.append("Barangay Voter's Registration Certificate\n");
        if (isCb12Checked) selectedDocs.append("Barangay Community Tax Certificate\n");

        reqDocs.setText(selectedDocs.toString());

        // Set an OnClickListener for the button
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRequest();
            }
        });
    }

    private void submitRequest() {
        // Get the reason from the EditText
        String reason = editTextText.getText().toString().trim();

        // You can add validation here if needed
        if (reason.isEmpty()) {
            // Notify user to enter a reason
            editTextText.setError("Please enter your reason");
            return;
        } else {
            Toast.makeText(nextforapplication.this,"Application Sent!", Toast.LENGTH_SHORT).show();
        }

        // Handle the request submission (e.g., send to server, save in database)
        // This is where you would implement your logic for handling the request

        // Optionally, navigate back to the previous screen or finish this activity
        finish(); // or navigate to another activity if needed
    }
}
