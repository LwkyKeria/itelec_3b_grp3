package com.example.barngyapp.backendapi;

import java.util.List;

public class AppointmentResponse {
    private String status;
    private List<Appointment> data;

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Appointment> getData() {
        return data;
    }

    public void setData(List<Appointment> data) {
        this.data = data;
    }
}

