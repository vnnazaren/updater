package com.vnazarenko.updater.launch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс DTO класса "Запуск" - Launch
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchDto {

    /**
     * Системный идентификатор экземпляра запуска
     */
    @Positive
    private Long id;

    /**
     * Id идентификатора расписания запуска
     */
    @NotNull(message = "Id планировщика - инициатора должен быть указан.")
    private Long schedulerId;

    /**
     * Статус выполнения задачи
     */
    @NotNull(message = "Статус запуска должен быть указан.")
    private StatusType status;

    /**
     * Дата и время начало выполнения
     */
    @JsonFormat
    private LocalDateTime startDateTime;

    /**
     * Дата и время окончания выполнения
     */
    @JsonFormat
    private LocalDateTime endDateTime;
}