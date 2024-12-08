package com.example.barngyapp.backendapi;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private boolean success;
    private String message;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("document_request_id")
    private String documentRequestId;

    @SerializedName("appointment_id")
    private String appointmentId;

    @SerializedName("error_code")
    private String errorCode;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("status")
    private String status;

    @SerializedName("appointment_status")
    private String appointmentStatus;

    // Default constructor
    public ApiResponse() {}

    // Constructor for testing/debugging
    public ApiResponse(boolean success, String message, String userId, String documentRequestId,
                       String appointmentId, String errorCode, String timestamp, String status,
                       String appointmentStatus) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.documentRequestId = documentRequestId;
        this.appointmentId = appointmentId;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
        this.status = status;
        this.appointmentStatus = appointmentStatus;
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

    // Getter and Setter methods for 'status'
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter methods for 'appointment_id'
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    // Getter and Setter methods for 'appointment_status'
    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", documentRequestId='" + documentRequestId + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                '}';
    }


}