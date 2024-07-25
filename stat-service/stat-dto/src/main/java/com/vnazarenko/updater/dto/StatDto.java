package com.vnazarenko.updater.dto;

/**
 * Класс DTO класса получения статистики STAT
 */
public record StatDto(String app,
                      String uri,
                      Long hits) {
}