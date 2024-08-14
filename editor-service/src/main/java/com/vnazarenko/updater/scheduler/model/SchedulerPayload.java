package com.vnazarenko.updater.scheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

import static com.vnazarenko.updater.util.Const.DATE_TIME_FORMAT;

/**
 * Класс DTO класса "Scheduler"
 */
public record SchedulerPayload(@Positive
                               Long id,
                               String name,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
                               LocalDateTime scheduleToStart,
                               Boolean isActive,
                               String description) {
}