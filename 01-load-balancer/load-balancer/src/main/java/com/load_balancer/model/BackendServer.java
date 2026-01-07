package com.load_balancer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class BackendServer {

    private String url;
    private boolean healthy = true;
    private int activeConnections = 0;

    public BackendServer(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public int getActiveConnections() {
        return activeConnections;
    }

    public void incrementConnections() {
        this.activeConnections++;
    }

    public void decrementConnections() {
        this.activeConnections--;
    }
}
