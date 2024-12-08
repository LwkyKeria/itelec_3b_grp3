package com.example.barngyapp.backends;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barngyapp.R;
import com.example.barngyapp.backendapi.Appointment;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private List<Appointment> appointments;

    public AppointmentAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        holder.reasonText.setText(appointment.getReason());
        holder.dateText.setText(appointment.getDate());
        holder.statusText.setText(appointment.getStatus());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView reasonText;
        TextView dateText;
        TextView statusText;

        ViewHolder(View itemView) {
            super(itemView);
            reasonText = itemView.findViewById(R.id.appointmentReason);
            dateText = itemView.findViewById(R.id.appointmentDate);
            statusText = itemView.findViewById(R.id.appointmentStatus);
        }
    }
}