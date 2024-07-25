package com.vnazarenko.updater.joblist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/**
 * Класс DTO класса "JobList"
 */
public record JobListPayload(

        @Positive
        Long id,

        @NotBlank(message = "Имя списка задач на выполнение должно быть указано.")
        String name,

        String code,

        @NotNull(message = "Статус списка задач на выполнение должен быть указан.")
        String status,

        @NotNull(message = "Id базы данных должен быть указан.")
        @Positive
        Long databaseId,

        @NotNull(message = "Id запуска должен быть указан.")
        @Positive
        Long launchId,

        @NotNull(message = "Id сценария должен быть указан.")
        @Positive
        Long scenarioId,

        String label,

        @NotNull(message = "Дата начала работы инстанса сценария должна быть указана.")
        @JsonFormat
        LocalDateTime startDateTime,

        @JsonFormat
        LocalDateTime endDateTime,

        String initContext
) {
}