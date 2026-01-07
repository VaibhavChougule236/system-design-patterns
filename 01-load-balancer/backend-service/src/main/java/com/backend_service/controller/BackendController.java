package com.backend_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from Backend running on port " + port;
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}
