package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    // Getter method for 'success'
    public boolean isSuccess() {
        return success;
    }

    // Getter method for 'message'
    public String getMessage() {
        return message;
    }

    // You can also add setters if necessary
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
