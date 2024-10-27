package com.example.barngyapp.eventadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barngyapp.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final Context context;
    private final List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the event item layout
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        // Get the current event
        Event event = eventList.get(position);

        // Set the event data
        holder.textEventTitle.setText(event.getTitle());
        holder.textEventDate.setText(event.getDate());
        holder.textEventLocation.setText(event.getLocation());
        holder.imageEvent.setImageResource(R.drawable.balogo); // Set your image here
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView textEventTitle, textEventDate, textEventLocation;
        ImageView imageEvent;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            textEventTitle = itemView.findViewById(R.id.text_event_title);
            textEventDate = itemView.findViewById(R.id.text_event_date);
            textEventLocation = itemView.findViewById(R.id.text_event_location);
            imageEvent = itemView.findViewById(R.id.image_event); // Ensure this ID matches your layout
        }
    }
}
