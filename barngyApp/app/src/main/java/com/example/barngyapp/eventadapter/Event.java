package com.example.barngyapp.eventadapter;

import java.util.Objects;

public class Event {

    private String title;
    private String description;
    private String date;
    private String location;

    // Constructor
    public Event(String title, String description, String date, String location) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Optional for debugging or logging
    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    // Optional equals() and hashCode() for comparing events
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return title.equals(event.title) && description.equals(event.description) && date.equals(event.date) && location.equals(event.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date, location);
    }
}
