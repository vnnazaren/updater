package com.vnazarenko.updater.stat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

import static com.vnazarenko.updater.util.Const.DATE_TIME_FORMAT;

/**
 * Класс DTO класса получения статистики HIT
 */
@Builder
public record StatIn(String app,
                     String ip,
                     String uri,
                     String method,
                     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
                     LocalDateTime timestamp) {
}