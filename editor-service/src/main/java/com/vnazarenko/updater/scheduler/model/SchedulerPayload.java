package com.vnazarenko.updater.scheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * Класс DTO класса "Scheduler"
 */
public record SchedulerPayload(@Positive
                               Long id,
                               String name,
                               @JsonFormat
                               LocalDateTime scheduleToStart,
                               Boolean isActive,
                               String description) {
}