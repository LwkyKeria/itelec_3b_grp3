package com.example.barngyapp.backendapi;

import java.util.List;

public class DocumentRequest {

    private String user_id;
    private List<String> document_ids;
    private String reason;

    // Constructor
    public DocumentRequest(String user_id, List<String> document_ids, String reason) {
        this.user_id = user_id;
        this.document_ids = document_ids;
        this.reason = reason;
    }

    // Getters
    public String getUser_id() {
        return user_id;
    }

    public List<String> getDocument_ids() {
        return document_ids;
    }

    public String getReason() {
        return reason;
    }
}
