package org.yug.societylink.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    private Map<String,String> validationErrors;

    // Constructor for handleRuntimeException
    public ApiError(int status, String error, String path) {
        this.timestamp = LocalDateTime.now(); // Automatically sets the exact time of the crash
        this.status = status;
        this.error = error;
        this.path = path;
    }

    //Constructor for handleUserNotFoundException
    public ApiError(int status,String error,String path,Map<String,String> validationErrors){
        this.timestamp = LocalDateTime.now(); // Automatically sets the exact time of the crash
        this.status = status;
        this.error = error;
        this.path = path;
        this.validationErrors=validationErrors;
    }

    // Getters and Setters (Generate these in your IDE)
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
    public void      setValidationErrors(Map<String,String> validationErrors) {
        this.validationErrors=validationErrors;
    }
}