package com.load_balancer.strategy;

import java.util.List;

import com.load_balancer.model.BackendServer;

public interface LoadBalancingStrategy {
    BackendServer select(List<BackendServer> servers);
}