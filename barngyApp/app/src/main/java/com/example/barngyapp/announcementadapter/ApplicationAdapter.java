package com.example.barngyapp.announcementadapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.barngyapp.R;

public class ApplicationAdapter extends CursorAdapter {

    // Constructor
    public ApplicationAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);  // Pass 0 for flags (you can set other flags if necessary)
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a new view for each item in the list
        return LayoutInflater.from(context).inflate(R.layout.users_item_application, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find the TextViews in the list item layout
        TextView documentNameTextView = view.findViewById(R.id.documentName);
        TextView statusTextView = view.findViewById(R.id.status);

        // Get the column indices for document_name and status
        int documentNameColumnIndex = cursor.getColumnIndex("document_name");
        int statusColumnIndex = cursor.getColumnIndex("status");

        // Check if the column index is valid (not -1)
        if (documentNameColumnIndex != -1 && statusColumnIndex != -1) {
            // Extract data from the cursor using the column indices
            String documentName = cursor.getString(documentNameColumnIndex);
            String status = cursor.getString(statusColumnIndex);

            // Set the data to the TextViews
            documentNameTextView.setText(documentName);
            statusTextView.setText(status);
        } else {
            // Handle case when columns are not found
            documentNameTextView.setText("Unknown Document");
            statusTextView.setText("Unknown Status");
        }
    }
}
