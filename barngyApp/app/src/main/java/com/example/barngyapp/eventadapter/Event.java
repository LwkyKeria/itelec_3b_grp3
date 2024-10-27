package com.example.barngyapp.eventadapter;

public class Event {
    private String title;
    private String date;
    private String location;

    // Constructor
    public Event(String title, String date, String location) {
        this.title = title;
        this.date = date;
        this.location = location;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
