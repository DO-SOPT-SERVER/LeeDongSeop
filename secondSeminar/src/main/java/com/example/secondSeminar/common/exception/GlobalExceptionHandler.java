package com.example.secondSeminar.common.exception;

import com.example.secondSeminar.common.exception.dto.ApiResponse;
import com.example.secondSeminar.common.exception.model.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.IOException;


@Slf4j
@RestControllerAdvice
@Component
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }

    /**
     * 500 INTERNEL_SERVER  // TODO 서비스 단에서 예외가 꼼꼼하게 처리된 상태에서 500 에러를 가장 마지막에 던지도록 처리
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponse<Exception> handleException(final Exception e, final HttpServletRequest request) throws IOException {

        log.error("500 error occured: {}", e.getMessage(), e);

        return ApiResponse.error(ErrorType.INTERNAL_SERVER_ERROR, e);
    }

    /**
     * CUSTOM_ERROR
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {

        log.error("BusinessmException occured: {}", e.getMessage(), e);

        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getErrorType(), e.getMessage()));
    }
}