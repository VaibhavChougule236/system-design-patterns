package com.load_balancer.strategy;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.load_balancer.model.BackendServer;

public class RoundRobinStrategy implements LoadBalancingStrategy {

    private AtomicInteger index = new AtomicInteger(0);

    @Override
    public BackendServer select(List<BackendServer> servers) {
        return servers.get(index.getAndIncrement() % servers.size());
    }
}
