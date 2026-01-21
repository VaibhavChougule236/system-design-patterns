package com.cdn_edge_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdn_edge_server.service.CdnCacheService;
//
//@RestController
//@RequestMapping("/cdn/assets")
//public class CdnController {
//
//    private final CdnCacheService cdnCacheService;
//
//    public CdnController(CdnCacheService cdnCacheService) {
//        this.cdnCacheService = cdnCacheService;
//    }
//
//    @GetMapping("/{name}")
//    public String getAsset(@PathVariable String name) {
//        return cdnCacheService.getAsset(name);
//    }
//
//    @PostMapping("/invalidate/{name}")
//    public String invalidate(@PathVariable String name) {
//        cdnCacheService.invalidate(name);
//        return "Cache invalidated for asset: " + name;
//    }
//}

@RestController
@RequestMapping("/cdn/assets")
public class CdnController {

    private final CdnCacheService service;

    public CdnController(CdnCacheService service) {
        this.service = service;
    }

    @GetMapping("/{name}")
    public String getAsset(@PathVariable String name) {
        return service.getAsset(name);
    }

    @PostMapping("/invalidate/{name}")
    public String invalidate(@PathVariable String name) {
        service.invalidate(name);
        return "Cache invalidated for " + name;
    }
}
