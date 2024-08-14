package com.vnazarenko.updater.launch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

import static com.vnazarenko.updater.util.Const.DATE_TIME_FORMAT;

/**
 * Класс DTO класса "Launch"
 */
public record LaunchPayload(@Positive
                            Long id,
                            @NotNull(message = "Id планировщика - инициатора должен быть указан.")
                            Long schedulerId,
                            @NotNull(message = "Статус запуска должен быть указан.")
                            StatusType status,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
                            LocalDateTime startDateTime,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
                            LocalDateTime endDateTime) {
}