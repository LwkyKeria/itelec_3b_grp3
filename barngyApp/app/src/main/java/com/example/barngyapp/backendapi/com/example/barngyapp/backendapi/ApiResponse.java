package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    // Default constructor
    public ApiResponse() {}

    // Constructor for easier testing and debugging
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter method for 'success'
    public boolean isSuccess() {
        return success;
    }

    // Getter method for 'message'
    public String getMessage() {
        return message;
    }

    // Setter methods (in case they are needed elsewhere)
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Override toString for better logging
    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }


}
