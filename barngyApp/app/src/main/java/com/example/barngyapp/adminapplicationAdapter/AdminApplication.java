package com.example.barngyapp.adminapplicationAdapter; // Ensure this is the correct package

public class AdminApplication {
    private final int id;
    private final String userName;
    private final String documentRequested;
    private final String reason;

    public AdminApplication(int id, String userName, String documentRequested, String reason) {
        this.id = id;
        this.userName = userName;
        this.documentRequested = documentRequested;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDocumentRequested() {
        return documentRequested;
    }

    public String getReason() {
        return reason;
    }
}
