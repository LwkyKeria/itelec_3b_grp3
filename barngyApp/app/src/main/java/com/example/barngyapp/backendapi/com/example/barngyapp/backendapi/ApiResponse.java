package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private boolean success;
    private String message;

    @SerializedName("user_id") // Backend sends this field for login/signup responses
    private String userId;

    @SerializedName("document_request_id") // Backend sends this field for document request submissions
    private String documentRequestId;

    @SerializedName("error_code") // New: Handles specific error codes from the backend
    private String errorCode;

    @SerializedName("timestamp") // New: Optional timestamp field from the backend
    private String timestamp;

    // Default constructor
    public ApiResponse() {}

    // Constructor for testing/debugging
    public ApiResponse(boolean success, String message, String userId, String documentRequestId, String errorCode, String timestamp) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.documentRequestId = documentRequestId;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }

    // Getter and Setter methods for 'success'
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Getter and Setter methods for 'message'
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter methods for 'user_id'
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter methods for 'document_request_id'
    public String getDocumentRequestId() {
        return documentRequestId;
    }

    public void setDocumentRequestId(String documentRequestId) {
        this.documentRequestId = documentRequestId;
    }

    // Getter and Setter methods for 'error_code'
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    // Getter and Setter methods for 'timestamp'
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Override toString for better logging/debugging
    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", documentRequestId='" + documentRequestId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
