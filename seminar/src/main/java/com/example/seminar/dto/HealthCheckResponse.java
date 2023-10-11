package com.example.seminar.dto;

public class HealthCheckResponse {

    private static final String STATUS = "OK";
    private String status;

    public HealthCheckResponse() {
        this.status = STATUS;
    }

    public String getStatus() {
        return status;
    }
}
