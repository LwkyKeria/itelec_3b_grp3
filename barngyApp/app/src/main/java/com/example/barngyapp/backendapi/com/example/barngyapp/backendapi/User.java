package com.example.barngyapp.backendapi;

public class User {
    private String username;
    private String password;


    // Constructor for login (only username and password)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters for all fields
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }


}
