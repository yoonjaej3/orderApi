package com.jyj.api.status.controller;

import com.jyj.api.common.response.GenericResponse;
import com.jyj.api.status.response.HealthResponse;
import com.jyj.api.status.response.MemoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @GetMapping("/health")
    public GenericResponse<HealthResponse> health() {
        return new GenericResponse<>("서버 상태", new HealthResponse());
    }

    @GetMapping("/memInfo")
    public GenericResponse<MemoryResponse> memInfo() {
        return new GenericResponse<>("서버 메모리", new MemoryResponse());
    }
}


