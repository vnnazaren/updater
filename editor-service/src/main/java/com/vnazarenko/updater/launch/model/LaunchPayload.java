package com.vnazarenko.updater.launch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * Класс DTO класса "Запуск" - Launch
 */
public record LaunchPayload(

        @Positive
        Long id,

        @NotNull(message = "Id планировщика - инициатора должен быть указан.")
        Long schedulerId,

        @NotNull(message = "Статус запуска должен быть указан.")
        StatusType status,

        @JsonFormat
        LocalDateTime startDateTime,

        @JsonFormat
        LocalDateTime endDateTime
) {
}