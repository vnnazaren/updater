package com.vnazarenko.updater.model;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Класс-маппер
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {

    public static Hit toHit(final HitDto hitDto) {
        return Hit.builder()
                .app(hitDto.app())
                .uri(hitDto.uri())
                .ipAddress(hitDto.ip())
                .hitDate(hitDto.timestamp())
                .build();
    }

    public static StatDto toUriStatDto(final Stat stat) {
        return new StatDto(stat.getApp(),
                stat.getUri(),
                stat.getHits());
    }
}