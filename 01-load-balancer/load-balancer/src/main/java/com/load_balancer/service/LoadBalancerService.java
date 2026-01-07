package com.load_balancer.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.load_balancer.model.BackendServer;
import com.load_balancer.strategy.RoundRobinStrategy;

@Service
public class LoadBalancerService {

    private final List<BackendServer> servers = List.of(
        new BackendServer("http://localhost:8081"),
        new BackendServer("http://localhost:8082")
    );

    private final RoundRobinStrategy strategy = new RoundRobinStrategy();
    private final RestTemplate restTemplate = new RestTemplate();

    public String forward() {
        List<BackendServer> healthyServers =
                servers.stream()
                        .filter(BackendServer::isHealthy)
                        .collect(Collectors.toList());

        if (healthyServers.isEmpty()) {
            return "No backend available";
        }

        BackendServer server = strategy.select(healthyServers);
        server.incrementConnections();

        try {
            return restTemplate.getForObject(
                server.getUrl() + "/api/hello",
                String.class
            );
        } finally {
            server.decrementConnections();
        }
    }
}