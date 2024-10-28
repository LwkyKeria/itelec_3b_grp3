package com.example.barngyapp.adminapplicationAdapter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barngyapp.R;
import java.util.ArrayList;
import java.util.List;

public class AdminApplicationMain extends AppCompatActivity implements AdminApplicationAdapter.OnApplicationActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_application);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewApplications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Use AdminApplication instead of Application
        List<AdminApplication> applications = new ArrayList<>();
        applications.add(new AdminApplication(12345, "John Doe", "Barangay Clearance", "Employment purposes"));
        applications.add(new AdminApplication(12346, "Jane Smith", "Barangay ID", "Government requirements"));

        AdminApplicationAdapter adapter = new AdminApplicationAdapter(applications, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onApprove(AdminApplication application) {
        // Handle approve action
    }

    @Override
    public void onDisapprove(AdminApplication application) {
        // Handle disapprove action
    }
}
