package com.cdn_edge_server.service;

import com.cdn_edge_server.model.CacheEntry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service
//public class CdnCacheService {
//
//    private static final long TTL = 10_000; // 10 seconds
//    private static final String ORIGIN_BASE_URL = "http://localhost:8080/assets/";
//
//    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public String getAsset(String assetName) {
//
//        CacheEntry entry = cache.get(assetName);
//
//        if (entry != null && !isExpired(entry)) {
//            log("CACHE HIT", assetName);
//            return entry.getContent();
//        }
//
//        if (entry != null) {
//            log("CACHE EXPIRED", assetName);
//            cache.remove(assetName);
//        } else {
//            log("CACHE MISS", assetName);
//        }
//
//        String content = fetchFromOrigin(assetName);
//        cache.put(assetName, new CacheEntry(content));
//
//        return content;
//    }
//
//    public void invalidate(String assetName) {
//        cache.remove(assetName);
//        log("CACHE INVALIDATED", assetName);
//    }
//
//    private boolean isExpired(CacheEntry entry) {
//        return System.currentTimeMillis() - entry.getCachedAt() > TTL;
//    }
//
//    private String fetchFromOrigin(String assetName) {
//        log("FETCHING FROM ORIGIN", assetName);
//        return restTemplate.getForObject(
//                ORIGIN_BASE_URL + assetName,
//                String.class
//        );
//    }
//
//    private void log(String message, String assetName) {
//        System.out.println("[CDN] " + message + " -> " + assetName);
//    }
//}

@Service
public class CdnCacheService {

    private static final long TTL = 10_000; // 10 seconds
    private static final String ORIGIN_URL = "http://localhost:8080/assets/";

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();

    public String getAsset(String name) {

        CacheEntry entry = cache.get(name);

        if (entry != null && !isExpired(entry)) {
            System.out.println("[CDN] CACHE HIT -> " + name);
            return entry.getContent();
        }

        if (entry != null) {
            System.out.println("[CDN] CACHE EXPIRED -> " + name);
            cache.remove(name);
        } else {
            System.out.println("[CDN] CACHE MISS -> " + name);
        }

        String content = fetchFromOrigin(name);
        cache.put(name, new CacheEntry(content));
        return content;
    }

    public void invalidate(String name) {
        cache.remove(name);
        System.out.println("[CDN] CACHE INVALIDATED -> " + name);
    }

    private boolean isExpired(CacheEntry entry) {
        return System.currentTimeMillis() - entry.getCachedAt() > TTL;
    }

    private String fetchFromOrigin(String name) {
        System.out.println("[CDN] FETCHING FROM ORIGIN -> " + name);
        return restTemplate.getForObject(ORIGIN_URL + name, String.class);
    }
}

