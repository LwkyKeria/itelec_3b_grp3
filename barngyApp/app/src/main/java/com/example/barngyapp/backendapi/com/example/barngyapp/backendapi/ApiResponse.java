package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("user_id") // Assuming the backend sends this field for login/signup requests
    private String userId;

    @SerializedName("document_request_id") // Assuming the backend sends this field for document request submissions
    private String documentRequestId;

    // Default constructor
    public ApiResponse() {}

    // Constructor for easier testing and debugging
    public ApiResponse(boolean success, String message, String userId, String documentRequestId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.documentRequestId = documentRequestId;
    }

    // Getter method for 'success'
    public boolean isSuccess() {
        return success;
    }

    // Getter method for 'message'
    public String getMessage() {
        return message;
    }

    // Getter method for 'user_id'
    public String getUserId() {
        return userId;
    }

    // Getter method for 'document_request_id'
    public String getDocumentRequestId() {
        return documentRequestId;
    }

    // Setter methods (if needed)
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDocumentRequestId(String documentRequestId) {
        this.documentRequestId = documentRequestId;
    }

    // Override toString for better logging
    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", user_id='" + userId + '\'' +
                ", document_request_id='" + documentRequestId + '\'' +
                '}';
    }
}
