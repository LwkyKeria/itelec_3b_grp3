package com.example.barngyapp.eventadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final List<Event> events;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    public EventAdapter(List<Event> events, OnItemClickListener listener) {
        this.events = events;
        this.listener = listener;

        if (events != null) {
            Log.d("EventAdapter", "Events list size: " + events.size());
        } else {
            Log.e("EventAdapter", "Events list is null");
        }
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event, listener);
    }

    @Override
    public int getItemCount() {
        return (events != null) ? events.size() : 0;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, date, location;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventTitle);
            date = itemView.findViewById(R.id.eventDate);
            location = itemView.findViewById(R.id.eventLocation);
        }

        public void bind(Event event, OnItemClickListener listener) {
            title.setText(event.getTitle());
            date.setText(event.getDate());
            location.setText(event.getLocation());

            itemView.setOnClickListener(v -> listener.onItemClick(event));
        }
    }
}
