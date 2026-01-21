package com.origin_asset_server.service;

import org.springframework.stereotype.Service;
import com.origin_asset_server.controller.AssetController;
import com.origin_asset_server.entity.Asset;
import com.origin_asset_server.repository.AssetRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssetService {

    private final AssetController assetController;

    private final AssetRepository repository;

    public AssetService(AssetRepository repository, AssetController assetController) {
        this.repository = repository;
        this.assetController = assetController;
    }

    public String fetchAsset(String name) throws InterruptedException {

        Thread.sleep(1500);

        Asset asset = repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        log.info("FETCHING ASSET -> " + name);
        return asset.getContent();
    }
}
