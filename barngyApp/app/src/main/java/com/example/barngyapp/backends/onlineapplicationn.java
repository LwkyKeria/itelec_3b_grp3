package com.example.barngyapp.backends;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;

public class onlineapplicationn extends AppCompatActivity {

    private CheckBox checkb1, checkb2, checkb3, checkb4, checkb5, checkb6, checkb7, checkb8,
            checkb9, checkb10, checkb11, checkb12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlineapplication);

        // Para sa CheckBoxes
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

        Button btnNextStep = findViewById(R.id.btnNext);


        setupClickListeners();


        btnNextStep.setOnClickListener(view -> NextStep());
    }

    private void setupClickListeners() {
        findViewById(R.id.barangayID).setOnClickListener(v -> showDocumentInfo("Barangay ID",
                "The Barangay ID is an identification document used for verifying personal information. It is often required for various administrative processes, including applications for government services and community engagement."));

        findViewById(R.id.brgyCertificateOfResidency).setOnClickListener(v -> showDocumentInfo("Certificate Of Residency",
                "The Certificate of Residency serves as proof of residence within a particular barangay or locality. It is often required for local transactions, job applications, and other administrative processes requiring proof of residence."));

        findViewById(R.id.brngyIndigency).setOnClickListener(v -> showDocumentInfo("Indigency",
                "The Indigency Certificate provides information and recognition for individuals or families living in poverty. It may include details on financial status and is often required for accessing government aid and assistance programs."));

        findViewById(R.id.brngyProtectionOrder).setOnClickListener(v -> showDocumentInfo("Protection Order",
                "The Protection Order is a legal document issued by a court to protect individuals from harassment or harm. It may restrict an individual from contacting or coming near the protected person and is crucial for ensuring personal safety."));

        findViewById(R.id.brngyBusinessClearance).setOnClickListener(v -> showDocumentInfo("Business Clearance",
                "The Business Clearance is an official document that certifies a business complies with local regulations and is authorized to operate within the barangay. It is often required for business permits and licenses."));

        findViewById(R.id.brgyBlotterReport).setOnClickListener(v -> showDocumentInfo("Blotter Report",
                "The Blotter Report documents incidents reported to local authorities, including criminal activities or disputes. It serves as an official record that can be used in legal proceedings and community safety assessments."));

        findViewById(R.id.brgyCertificateToFileAction).setOnClickListener(v -> showDocumentInfo("Certificate To File Action",
                "The Certificate To File Action is required for initiating legal proceedings. It certifies that a specific action has been officially recognized, allowing individuals to proceed with filing their cases in court."));

        findViewById(R.id.brngyPermitForEvents).setOnClickListener(v -> showDocumentInfo("Permit For Events",
                "The Permit For Events is necessary for organizing public gatherings, ensuring that events comply with local regulations regarding safety, noise, and community welfare. It is a prerequisite for hosting community activities."));

        findViewById(R.id.brgyVoterRegistrationCertificate).setOnClickListener(v -> showDocumentInfo("Voter Registration Certificate",
                "The Voter Registration Certificate confirms a person's eligibility to vote in elections. It is often required for voter registration and serves as proof of identity and residency when voting."));

        findViewById(R.id.brgyCommunityTaxCertificate).setOnClickListener(v -> showDocumentInfo("Community Tax Certificate",
                "The Community Tax Certificate serves as proof of payment of local taxes. It is often required for various government transactions and verifies an individual's contribution to local development."));

        findViewById(R.id.brngyClearance).setOnClickListener(v -> showDocumentInfo("Barangay Clearance",
                "A Barangay Clearance is used to verify that an individual has no pending legal issues or obligations in a specific jurisdiction. It is often required for job applications, travel requirements, or other legal matters."));

        findViewById(R.id.brngyCertificate).setOnClickListener(v -> showDocumentInfo("Barangay Certificate",
                "The Barangay Certificate may refer to various types of documentation that confirm specific achievements, qualifications, or statuses within legal or administrative frameworks. It can be used for employment, school applications, and other official processes."));

    }

    private void showDocumentInfo(String title, String description) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_document_info);
        dialog.setCancelable(true);

        TextView titleTextView = dialog.findViewById(R.id.documentTitle);
        TextView descriptionTextView = dialog.findViewById(R.id.documentDescription);
        Button closeButton = dialog.findViewById(R.id.closeButton);

        titleTextView.setText(title);
        descriptionTextView.setText(description);

        closeButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void NextStep() {
        // status of checkbox
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
        boolean isCb12Checked = checkb12.isChecked();

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
        intent.putExtra("isCb12Checked", isCb12Checked);

        startActivity(intent);
    }
}
