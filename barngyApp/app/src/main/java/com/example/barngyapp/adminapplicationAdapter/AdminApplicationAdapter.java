package com.example.barngyapp.adminapplicationAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.barngyapp.R; // Change to your actual package name
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdminApplicationAdapter extends RecyclerView.Adapter<AdminApplicationAdapter.ApplicationViewHolder> {

    private final List<AdminApplication> applicationList; // Changed from Application to AdminApplication
    private final OnApplicationActionListener actionListener;

    public AdminApplicationAdapter(List<AdminApplication> applicationList, OnApplicationActionListener actionListener) {
        this.applicationList = applicationList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item_application, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        AdminApplication application = applicationList.get(position); // Changed from Application to AdminApplication
        holder.tvApplicationID.setText("Application ID: " + application.getId());
        holder.tvUserName.setText("User: " + application.getUserName());
        holder.tvDocumentRequested.setText("Document Requested: " + application.getDocumentRequested());
        holder.tvReasonForRequest.setText("Reason: " + application.getReason());

        holder.btnApprove.setOnClickListener(v -> actionListener.onApprove(application));
        holder.btnDisapprove.setOnClickListener(v -> actionListener.onDisapprove(application));
    }

    @Override
    public int getItemCount() {
        return applicationList.size();
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView tvApplicationID, tvUserName, tvDocumentRequested, tvReasonForRequest;
        Button btnApprove, btnDisapprove;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApplicationID = itemView.findViewById(R.id.tvApplicationID);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvDocumentRequested = itemView.findViewById(R.id.tvDocumentRequested);
            tvReasonForRequest = itemView.findViewById(R.id.tvReasonForRequest);
            btnApprove = itemView.findViewById(R.id.btnApprove);
            btnDisapprove = itemView.findViewById(R.id.btnDisapprove);
        }
    }

    public interface OnApplicationActionListener {
        void onApprove(AdminApplication application); // Changed from Application to AdminApplication
        void onDisapprove(AdminApplication application); // Changed from Application to AdminApplication
    }
}
