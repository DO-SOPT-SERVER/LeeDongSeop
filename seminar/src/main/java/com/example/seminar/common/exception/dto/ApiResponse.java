package com.example.seminar.common.exception.dto;

import com.example.seminar.common.exception.ErrorType;
import com.example.seminar.common.exception.SuccessType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"code", "status", "success"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse {

    private final int code;
    private final String status;
    private final boolean success;

    public static ApiResponse success(SuccessType successType) {
        return new ApiResponse(successType.getHttpStatusCode(), successType.getMessage(), true);
    }

    public static ApiResponse error(ErrorType errorType) {
        return new ApiResponse(errorType.getHttpStatusCode(), errorType.getMessage(), false);
    }

    public static ApiResponse error(ErrorType errorType, String message) {
        return new ApiResponse(errorType.getHttpStatusCode(), message, false);
    }
}