package com.vnazarenko.updater.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

import static com.vnazarenko.updater.dto.Const.DATE_TIME_FORMAT;

/**
 * Класс DTO класса получения статистики HIT
 */
@Builder
public record HitDto
        (String app,
         String ip,
         String uri,
         String httpMethod,
         @JsonFormat(pattern = DATE_TIME_FORMAT)
         LocalDateTime timestamp) {
}