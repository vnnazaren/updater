package com.vnazarenko.updater.service;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс класса-сервиса HIT
 */
public interface HitService {

    void saveHit(HitDto hitDtoRequest);

    List<StatDto> getHitsInfo(List<String> uris,
                              Boolean unique,
                              LocalDateTime start,
                              LocalDateTime end);
}