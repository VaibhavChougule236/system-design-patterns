package com.origin_asset_server.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Asset {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(length = 5000)
    private String content;

    private LocalDateTime updatedAt;
}
