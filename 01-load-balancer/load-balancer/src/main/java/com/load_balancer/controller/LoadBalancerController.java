package com.load_balancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.load_balancer.service.LoadBalancerService;

@RestController
public class LoadBalancerController {

    private final LoadBalancerService service;

    public LoadBalancerController(LoadBalancerService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return service.forward();
    }
}