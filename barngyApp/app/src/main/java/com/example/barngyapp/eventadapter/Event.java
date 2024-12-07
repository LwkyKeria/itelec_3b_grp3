package com.example.barngyapp.eventadapter;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("event_id")
    private int eventId;

    @SerializedName("event_title")
    private String title;

    @SerializedName("event_description")
    private String description;

    @SerializedName("event_date")
    private String date;

    @SerializedName("event_time")
    private String time;

    @SerializedName("event_location")
    private String location;

    @SerializedName("created_time")
    private String createdTime;

    // Default constructor required for JSON parsing
    public Event() {}

    // Constructor with all fields
    public Event(int eventId, String title, String description, String date,
                 String time, String location, String createdTime) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.createdTime = createdTime;
    }

    // Getters
    public int getEventId() { return eventId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public String getCreatedTime() { return createdTime; }

    // Setters
    public void setEventId(int eventId) { this.eventId = eventId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setLocation(String location) { this.location = location; }
    public void setCreatedTime(String createdTime) { this.createdTime = createdTime; }
}