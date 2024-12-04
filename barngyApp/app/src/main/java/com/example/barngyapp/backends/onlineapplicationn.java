package com.example.barngyapp.backends;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;

import java.util.ArrayList;
import java.util.List;

public class onlineapplicationn extends AppCompatActivity {

    private Button btnNextStep;
    private List<String> selectedDocuments = new ArrayList<>();
    private CheckBox checkboxBarangayID, checkboxBarangayClearance, checkboxBarangayCertificate, checkboxBarangayCertificateResidency;
    private CheckBox checkboxBarangayIndigency, checkboxBarangayProtectionOrder, checkboxBarangayBusinessClearance;
    private CheckBox checkboxBarangayBlotterReport, checkboxBarangayCertificateToFileAction, checkboxBarangayPermitForEvents;
    private CheckBox checkboxBarangayVotersRegCertificate, checkboxBarangayCommunityTaxCertificate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlineapplication);

        // Initialize checkboxes for each document
        btnNextStep = findViewById(R.id.btnNext);
        checkboxBarangayID = findViewById(R.id.cb1);
        checkboxBarangayClearance = findViewById(R.id.cb2);
        checkboxBarangayCertificate = findViewById(R.id.cb3);
        checkboxBarangayCertificateResidency = findViewById(R.id.cb4);
        checkboxBarangayIndigency = findViewById(R.id.cb5);
        checkboxBarangayProtectionOrder = findViewById(R.id.cb6);
        checkboxBarangayBusinessClearance = findViewById(R.id.cb7);
        checkboxBarangayBlotterReport = findViewById(R.id.cb8);
        checkboxBarangayCertificateToFileAction = findViewById(R.id.cb9);
        checkboxBarangayPermitForEvents = findViewById(R.id.cb10);
        checkboxBarangayVotersRegCertificate = findViewById(R.id.cb11);
        checkboxBarangayCommunityTaxCertificate = findViewById(R.id.cb12);

        // Set listener for the Next Step button
        btnNextStep.setOnClickListener(v -> {
            // Clear previously selected documents
            selectedDocuments.clear();

            // Add selected document IDs to the list
            if (checkboxBarangayID.isChecked()) selectedDocuments.add("Barangay ID");
            if (checkboxBarangayClearance.isChecked()) selectedDocuments.add("Barangay Clearance");
            if (checkboxBarangayCertificate.isChecked()) selectedDocuments.add("Barangay Certificate");
            if (checkboxBarangayCertificateResidency.isChecked()) selectedDocuments.add("Barangay Certificate Residency");
            if (checkboxBarangayIndigency.isChecked()) selectedDocuments.add("Barangay Indigency");
            if (checkboxBarangayProtectionOrder.isChecked()) selectedDocuments.add("Barangay Protection Order");
            if (checkboxBarangayBusinessClearance.isChecked()) selectedDocuments.add("Barangay Business Clearance");
            if (checkboxBarangayBlotterReport.isChecked()) selectedDocuments.add("Barangay Blotter Report");
            if (checkboxBarangayCertificateToFileAction.isChecked()) selectedDocuments.add("Barangay Certificate To FileAction");
            if (checkboxBarangayPermitForEvents.isChecked()) selectedDocuments.add("Barangay Permit For Events");
            if (checkboxBarangayVotersRegCertificate.isChecked()) selectedDocuments.add("Barangay Voters Registration Certificate");
            if (checkboxBarangayCommunityTaxCertificate.isChecked()) selectedDocuments.add("Barangay Community Tax Certificate");

            // Check if at least one document is selected
            if (selectedDocuments.size() > 0) {
                // Pass the selected documents to the next activity
                Intent intent = new Intent(onlineapplicationn.this, nextforapplication.class);
                intent.putStringArrayListExtra("selectedDocuments", new ArrayList<>(selectedDocuments));
                startActivity(intent);
            } else {
                // If no documents are selected, show a toast
                Toast.makeText(onlineapplicationn.this, "Please select at least one document.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
