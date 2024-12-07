package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

public class BarangayOfficial {
    @SerializedName("official_id")
    private int id;

    @SerializedName("official_name")
    private String name;

    @SerializedName("official_position")
    private String position;

    @SerializedName("contact_info")
    private String contactInfo;

    // Constructor
    public BarangayOfficial(int id, String name, String position, String contactInfo) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.contactInfo = contactInfo;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public String getContactInfo() { return contactInfo; }

    @Override
    public String toString() {
        return position + ": " + name;
    }
}