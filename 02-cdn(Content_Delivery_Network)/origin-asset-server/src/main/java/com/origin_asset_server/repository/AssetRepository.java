package com.origin_asset_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.origin_asset_server.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
	Optional<Asset> findByName(String name);
}
