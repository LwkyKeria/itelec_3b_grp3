package com.example.barngyapp.eventadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.barngyapp.R;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.titleTextView.setText(event.getTitle());

        // Format date and time
        try {
            // Parse the date
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
            Date eventDate = inputDateFormat.parse(event.getDate());
            String formattedDate = outputDateFormat.format(eventDate);

            // Parse the time
            SimpleDateFormat inputTimeFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
            SimpleDateFormat outputTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
            Date eventTime = inputTimeFormat.parse(event.getTime());
            String formattedTime = outputTimeFormat.format(eventTime);

            holder.dateTextView.setText(String.format("%s at %s", formattedDate, formattedTime));
        } catch (ParseException e) {
            holder.dateTextView.setText(String.format("%s %s", event.getDate(), event.getTime()));
        }

        holder.locationTextView.setText(event.getLocation());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void updateEvents(List<Event> newEvents) {
        this.eventList = newEvents;
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dateTextView;
        TextView locationTextView;

        EventViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.eventTitle);
            dateTextView = itemView.findViewById(R.id.eventDate);
            locationTextView = itemView.findViewById(R.id.eventLocation);
        }
    }
}