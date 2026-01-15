package com.origin_asset_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @GetMapping("/{name}")
    public ResponseEntity<String> getAsset(@PathVariable String name) {

        try {
            Thread.sleep(1500);

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("assets/" + name + ".txt");

            if (inputStream == null) {
                return ResponseEntity.notFound().build();
            }

            String content = new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

            return ResponseEntity.ok(content);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
