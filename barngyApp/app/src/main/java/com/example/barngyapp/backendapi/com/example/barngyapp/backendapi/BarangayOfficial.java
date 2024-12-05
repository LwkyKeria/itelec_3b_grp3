package com.example.barngyapp.backendapi;

public class BarangayOfficial {
    private int id;
    private String name;
    private String position;
    private String contact;

    public BarangayOfficial(int id, String name, String position, String contact) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.contact = contact;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getContact() {
        return contact;
    }

    // Override toString to display the official's name in the Spinner
    @Override
    public String toString() {
        return name;  // This will display the name of the official in the Spinner
    }
}
