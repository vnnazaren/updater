package com.vnazarenko.updater.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import static com.vnazarenko.updater.dto.Const.DATE_TIME_FORMAT;

/**
 * Класс DTO класса получения статистики HIT
 */
public record HitDto

        (String app,

         String uri,

         String ip,

         @JsonFormat(pattern = DATE_TIME_FORMAT)
         LocalDateTime timestamp) {
}