package com.example.barngyapp.backendapi;

public class Appointment {
    private int id;
    private String reason;
    private String date;
    private String status;
    private Official official;
    private String timestamp;

    // Getters
    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Official getOfficial() {
        return official;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOfficial(Official official) {
        this.official = official;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}