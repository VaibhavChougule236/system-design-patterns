package com.cdn_edge_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CacheEntry {

    private final String content;
    private final long cachedAt;

	public CacheEntry(String content) {
		this.content = content;
		this.cachedAt = System.currentTimeMillis();
	}
}