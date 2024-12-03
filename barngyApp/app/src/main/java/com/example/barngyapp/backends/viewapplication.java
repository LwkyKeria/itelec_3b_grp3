package com.example.barngyapp.backends;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barngyapp.R;
import com.example.barngyapp.announcementadapter.ApplicationAdapter;

public class viewapplication extends AppCompatActivity {

    private ListView listViewApplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_view_application); // Ensure you have this layout file

        // Initialize ListView
        listViewApplications = findViewById(R.id.listViewApplications);

        // Fetch application data from the database
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getApplications();  // Call the method to get applications

        if (cursor != null && cursor.getCount() > 0) {
            // Create and set an adapter to the ListView
            ApplicationAdapter adapter = new ApplicationAdapter(this, cursor);
            listViewApplications.setAdapter(adapter);
        } else {
            // No data found
            Toast.makeText(this, "No applications found", Toast.LENGTH_SHORT).show();
        }

        dbHelper.close();
        if (cursor != null) {
            cursor.close();  // Close the cursor after usage
        }
    }
}
