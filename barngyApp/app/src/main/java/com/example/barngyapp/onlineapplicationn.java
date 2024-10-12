package com.example.barngyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class onlineapplicationn extends AppCompatActivity {


    private CheckBox checkb1, checkb2, checkb3, checkb4, checkb5, checkb6, checkb7, checkb8,
            checkb9, checkb10, checkb11, checkb12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlineapplication); // Ensure this matches your XML layout filename

        // Initialize UI elements
        checkb1 = findViewById(R.id.cb1);
        checkb2 = findViewById(R.id.cb2);
        checkb3 = findViewById(R.id.cb3);
        checkb4 = findViewById(R.id.cb4);
        checkb5 = findViewById(R.id.cb5);
        checkb6 = findViewById(R.id.cb6);
        checkb7 = findViewById(R.id.cb7);
        checkb8 = findViewById(R.id.cb8);
        checkb9 = findViewById(R.id.cb9);
        checkb10 = findViewById(R.id.cb10);
        checkb11 = findViewById(R.id.cb11);
        checkb12 = findViewById(R.id.cb12);
        Button btnNextStep = findViewById(R.id.btnNext); // Ensure you have this button in your XML

        // Set click listeners for CheckBoxes
        checkb1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay ID Checked" : "Barangay ID Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Clearance Checked" : "Barangay Clearance Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Certificate Checked" : "Barangay Certificate Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Certificate of Residency Checked" : "Barangay Certificate of Residency Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Indigency Checked" : "Barangay Indigency Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb6.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Protection Order Checked" : "Barangay Protection Order Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb7.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Business Clearance Checked" : "Barangay Business Clearance Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb8.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Blotter Report Checked" : "Barangay Blotter Report Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb9.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Certification to File Action Checked" : "Barangay Certification to File Action Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb10.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Permits for Events Checked" : "Barangay Permits for Events Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb11.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Voter's Registration Certificate Checked" : "Barangay Voter's Registration Certificate Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        checkb12.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Barangay Community Tax Certificate Checked" : "Barangay Community Tax Certificate Unchecked";
            Toast.makeText(onlineapplicationn.this, message, Toast.LENGTH_SHORT).show();
        });

        // Set click listener for submit button
        btnNextStep.setOnClickListener(view -> NextStep());
    }

    private void NextStep() {
        // Check the status of all checkboxes
        boolean isCb1Checked = checkb1.isChecked();
        boolean isCb2Checked = checkb2.isChecked();
        boolean isCb3Checked = checkb3.isChecked();
        boolean isCb4Checked = checkb4.isChecked();
        boolean isCb5Checked = checkb5.isChecked();
        boolean isCb6Checked = checkb6.isChecked();
        boolean isCb7Checked = checkb7.isChecked();
        boolean isCb8Checked = checkb8.isChecked();
        boolean isCb9Checked = checkb9.isChecked();
        boolean isCb10Checked = checkb10.isChecked();
        boolean isCb11Checked = checkb11.isChecked();
        boolean isCb12Checked = checkb12.isChecked(); // Adjust the naming to match the checkbox you want to check

        // Check if at least one checkbox is checked
        if (!isCb1Checked && !isCb2Checked && !isCb3Checked && !isCb4Checked &&
                !isCb5Checked && !isCb6Checked && !isCb7Checked && !isCb8Checked &&
                !isCb9Checked && !isCb10Checked && !isCb11Checked && !isCb12Checked) {
            Toast.makeText(this, "Please select at least one document.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create an Intent to navigate to the next activity
        Intent intent = new Intent(onlineapplicationn.this, nextforapplication.class);
        // Pass the data to the next activity
        intent.putExtra("isCb1Checked", isCb1Checked);
        intent.putExtra("isCb2Checked", isCb2Checked);
        intent.putExtra("isCb3Checked", isCb3Checked);
        intent.putExtra("isCb4Checked", isCb4Checked);
        intent.putExtra("isCb5Checked", isCb5Checked);
        intent.putExtra("isCb6Checked", isCb6Checked);
        intent.putExtra("isCb7Checked", isCb7Checked);
        intent.putExtra("isCb8Checked", isCb8Checked);
        intent.putExtra("isCb9Checked", isCb9Checked);
        intent.putExtra("isCb10Checked", isCb10Checked);
        intent.putExtra("isCb11Checked", isCb11Checked);
        intent.putExtra("isCb12Checked", isCb12Checked); // Adjust the naming to match the checkbox you want to check

        startActivity(intent);
    }
}