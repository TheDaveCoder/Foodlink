package com.infomanagers.app.Model;

public class ResponseModel {
    private String statusCode;
    private String message;
    private String brgyID;
    private String staffID;
    private String date_of_creation;
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getBrgyID() {
        return brgyID;
    }
    public void setBrgyID(String brgyID) {
        this.brgyID = brgyID;
    }
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getDate_of_creation() {
        return date_of_creation;
    }
    public void setDate_of_creation(String date_of_creation) {
        this.date_of_creation = date_of_creation;
    }
}
