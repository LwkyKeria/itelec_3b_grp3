package com.example.barngyapp.applicationadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.barngyapp.R;

import java.util.List;

public class ApplicationAdapter extends BaseAdapter {
    private Context context;
    private List<Application> applicationList;

    public ApplicationAdapter(Context context, List<Application> applicationList) {
        this.context = context;
        this.applicationList = applicationList;
    }

    @Override
    public int getCount() {
        return applicationList.size();
    }

    @Override
    public Object getItem(int position) {
        return applicationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.users_item_application, parent, false);
        }

        Application application = (Application) getItem(position);

        // Bind UI elements
        TextView documentName = convertView.findViewById(R.id.documentName);
        TextView status = convertView.findViewById(R.id.status);
        TextView applicationDate = convertView.findViewById(R.id.applicationDate);
        CardView cardView = convertView.findViewById(R.id.cardView);

        // Safeguard against null values
        String reason = application.getReason() != null ? application.getReason() : "No reason provided";
        String statusText = application.getStatus() != null ? application.getStatus() : "Pending";
        String date = application.getDate() != null ? application.getDate() : "Unknown date";

        // Set data to views
        documentName.setText(reason);
        status.setText(statusText);
        applicationDate.setText("Date: " + date);

        // Set card background color based on status
        int backgroundColor;
        switch (statusText.toLowerCase()) {
            case "approved":
                backgroundColor = Color.parseColor("#4CAF50"); // Green for approved
                break;
            case "rejected":
                backgroundColor = Color.parseColor("#F44336"); // Red for rejected
                break;
            default:
                backgroundColor = Color.parseColor("#FF9800"); // Orange for pending or other
        }
        cardView.setCardBackgroundColor(backgroundColor);

        return convertView;
    }
}
