package com.vnazarenko.updater.scheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс DTO класса "Планировщик" - Scheduler
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerDto {

    /**
     * Системный идентификатор настроек планировщика
     */
    @Positive
    private Long id;

    /**
     * Имя планировщика
     */
    private String name;

    /**
     * Запланированное время старта
     */
    @JsonFormat
    private LocalDateTime scheduleToStart;

    /**
     * Взведён ли планировщик
     */
    private Boolean isActive;

    /**
     * Описание планировщика
     */
    private String description;
}