package com.example.barngyapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.Calendar;

public class onlineappointmentt extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editReason;
    private EditText editTextDate; // To display the selected date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onlineappointment);

        // Initialize views
        Spinner spinner = findViewById(R.id.barangay_officials);
        editReason = findViewById(R.id.reason);
        editTextDate = findViewById(R.id.selectedDate); // EditText to display the date

        Button btnreq = findViewById(R.id.reqForApp);
        Button btnDatePicker = findViewById(R.id.datePickerButton);

        // Set up the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.barangay_off,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Set button click listeners
        btnreq.setOnClickListener(v -> validateInput());
        btnDatePicker.setOnClickListener(this::openDatePicker); // Pass 'this::openDatePicker' as reference
    }

    // Handle item selection in spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Optional: Handle case where nothing is selected
    }

    // Validate input when button is clicked
    private void validateInput() {
        String reason = editReason.getText().toString().trim();
        String date = editTextDate.getText().toString().trim(); // Get the selected date

        if (reason.isEmpty()) {
            Toast.makeText(this, "Please type your reason first", Toast.LENGTH_SHORT).show();
        } else if (date.isEmpty()) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
        } else {
            // All inputs are valid
            Toast.makeText(this, "Request sent successfully!\nReason: " + reason + "\nDate: " + date, Toast.LENGTH_SHORT).show();
            // Add logic to send the request with reason and date here
        }
    }

    // Open date picker dialog
    public void openDatePicker(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view1, selectedYear, selectedMonth, selectedDay) -> {
                    // Set the selected date to the EditText
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    editTextDate.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }
}
