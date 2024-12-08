package com.example.barngyapp.applicationadapter;

public class Application {
    private int id;  // Request or application ID
    private String reason;  // Reason for the application/request
    private String date;  // Application date
    private String status;  // Status of the application
    private String documentTitle;  // Document title associated with the application

    // Full constructor with null safety
    public Application(int id, String reason, String date, String status, String documentTitle) {
        this.id = id;
        this.reason = (reason != null) ? reason : "No reason provided";
        this.date = (date != null) ? date : "Unknown date";
        this.status = (status != null) ? status : "Pending";
        this.documentTitle = (documentTitle != null) ? documentTitle : "No document title";
    }

    // Getter methods for accessing fields
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

    public String getDocumentTitle() {
        return documentTitle;
    }

    // Debugging utility method
    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", documentTitle='" + documentTitle + '\'' +
                '}';
    }
}
