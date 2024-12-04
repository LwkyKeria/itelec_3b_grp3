package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocumentRequest {
    @SerializedName("user_id")  // This ensures the key is 'user_id' (with underscore)
    private int userId;

    @SerializedName("document_ids")  // This ensures the key is 'document_ids' (with underscore)
    private List<Integer> documentIds;

    @SerializedName("reason")  // This ensures the key is 'reason'
    private String reason;  // String is correct

    // Constructor
    public DocumentRequest(int userId, List<Integer> documentIds, String reason) {
        this.userId = userId;
        this.documentIds = documentIds;
        this.reason = reason;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public List<Integer> getDocumentIds() {
        return documentIds;
    }

    public String getReason() {
        return reason;
    }
}

