package com.example.secondSeminar.common.dto;

import com.example.secondSeminar.common.exception.enums.ErrorType;
import com.example.secondSeminar.common.exception.enums.SuccessType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonPropertyOrder({"code", "status", "success", "data"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int code;
    private final String status;
    private final boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static ApiResponse<?> success(SuccessType successType) {
        return new ApiResponse<>(successType.getHttpStatusCode(), successType.getMessage(), true);
    }

    public static <T> ApiResponse<T> success(SuccessType successType, T data) {
        return new ApiResponse<T>(successType.getHttpStatusCode(), successType.getMessage(), true, data);
    }

    public static ApiResponse<?> error(ErrorType errorType) {
        return new ApiResponse<>(errorType.getHttpStatusCode(), errorType.getMessage(), false);
    }

    public static ApiResponse<?> error(ErrorType errorType, String message) {
        return new ApiResponse<>(errorType.getHttpStatusCode(), message, false);
    }

    public static <T> ApiResponse<T> error(ErrorType errorType, String message, T data) {
        return new ApiResponse<>(errorType.getHttpStatusCode(), message, false, data);
    }

    public static <T> ApiResponse<Exception> error(ErrorType errorType, Exception e) {
        return new ApiResponse<>(errorType.getHttpStatusCode(), errorType.getMessage(), false, e);
    }

    public static <T> ApiResponse<T> error(ErrorType errorType, T data) {
        return new ApiResponse<>(errorType.getHttpStatusCode(), errorType.getMessage(), false, data);
    }
}