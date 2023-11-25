package com.example.secondSeminar.common.exception.model;

import com.example.secondSeminar.common.exception.enums.ErrorType;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorType errorType;

    public BusinessException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public int getHttpStatus() {
        return errorType.getHttpStatusCode();
    }
}
