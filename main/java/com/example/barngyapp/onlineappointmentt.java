package com.example.barngyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class onlineappointmentt extends AppCompatActivity {

    String[] brgyOfficials = {
            "Barangay Captain",
            "Barangay Councilors",
            "Youth Council Chairman",
            "Barangay Secretary",
            "Barangay Treasurer",
            "Barangay Health Workers",
            "Barangay Tanod"
    };

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlineappointment);

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, brgyOfficials);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(onlineappointmentt.this, "Selected Barangay Official: " + item, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
