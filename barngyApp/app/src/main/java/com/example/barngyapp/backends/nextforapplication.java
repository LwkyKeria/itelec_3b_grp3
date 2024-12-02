package com.example.barngyapp.backends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.backends.DBHelper; // Assuming you have a DBHelper class

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
                submitRequest(isCb1Checked, isCb2Checked, isCb3Checked, isCb4Checked, isCb5Checked,
                        isCb6Checked, isCb7Checked, isCb8Checked, isCb9Checked, isCb10Checked,
                        isCb11Checked, isCb12Checked);
            }
        });
    }

    private void submitRequest(boolean isCb1Checked, boolean isCb2Checked, boolean isCb3Checked,
                               boolean isCb4Checked, boolean isCb5Checked, boolean isCb6Checked,
                               boolean isCb7Checked, boolean isCb8Checked, boolean isCb9Checked,
                               boolean isCb10Checked, boolean isCb11Checked, boolean isCb12Checked) {
        // Get the reason from the EditText
        String reason = editTextText.getText().toString().trim();

        // Validate reason input
        if (reason.isEmpty()) {
            // Notify user to enter a reason
            editTextText.setError("Please enter your reason");
            return;
        } else {
            Toast.makeText(nextforapplication.this, "Application Sent!", Toast.LENGTH_SHORT).show();
        }

        // Insert the selected documents into the database with a "Pending" status
        DBHelper dbHelper = new DBHelper(nextforapplication.this);

        if (isCb1Checked) dbHelper.insertApplication("Barangay ID", "Pending");
        if (isCb2Checked) dbHelper.insertApplication("Barangay Clearance", "Pending");
        if (isCb3Checked) dbHelper.insertApplication("Barangay Certificate", "Pending");
        if (isCb4Checked) dbHelper.insertApplication("Barangay Certificate for Residency", "Pending");
        if (isCb5Checked) dbHelper.insertApplication("Barangay Indigency", "Pending");
        if (isCb6Checked) dbHelper.insertApplication("Barangay Protection Order", "Pending");
        if (isCb7Checked) dbHelper.insertApplication("Barangay Business Clearance", "Pending");
        if (isCb8Checked) dbHelper.insertApplication("Barangay Blotter Report", "Pending");
        if (isCb9Checked) dbHelper.insertApplication("Barangay Certification to File Action", "Pending");
        if (isCb10Checked) dbHelper.insertApplication("Barangay Permits for Events", "Pending");
        if (isCb11Checked) dbHelper.insertApplication("Barangay Voter's Registration Certificate", "Pending");
        if (isCb12Checked) dbHelper.insertApplication("Barangay Community Tax Certificate", "Pending");

        dbHelper.close();

        // Optionally, navigate to the next screen (ViewApplicationStatus)
        Intent intent = new Intent(nextforapplication.this, viewapplication.class);
        startActivity(intent);

        // Finish current activity if you don't need to return to it
        finish();
    }
}
