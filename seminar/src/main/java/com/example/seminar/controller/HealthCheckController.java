package com.example.seminar.controller;

import com.example.seminar.common.exception.SuccessType;
import com.example.seminar.common.exception.dto.ApiResponse;
import com.example.seminar.common.exception.dto.HealthCheckResponse;
import com.example.seminar.sample.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("/v1")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/v2")
    public ResponseEntity<String> healthCheckV2() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/v3")
    public String healtchCheckV3() {
        Person person = new Person("최", "윤한");
        Person person2 = Person.builder()
                .lastName("최")
                .firstName("윤한")
                .build();

        return "OK";
    }

    @GetMapping("/v4")
    public ResponseEntity<Map<String, String>> healthCheckV4() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v5")
    public ResponseEntity<HealthCheckResponse> healthCheckV5() {
        return ResponseEntity.ok(new HealthCheckResponse());
    }

    @GetMapping("/advanced")
    public ApiResponse<?> healthCheckAdvanced() {
        return ApiResponse.success(SuccessType.PROCESS_SUCCESS);
    }
}
