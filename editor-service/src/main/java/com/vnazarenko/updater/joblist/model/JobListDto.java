package com.vnazarenko.updater.joblist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Класс DTO класса "Инстанс Сценарий" - JobList
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListDto {

    /**
     * Системный идентификатор настроек сценария
     */
    @Positive
    private Long id;

    /**
     * Имя сценария
     */
    @NotBlank(message = "Имя инстанса сценария должно быть указано.")
    private String name;

    /**
     * Внешний идентификатор списка задач (необязательно)
     */
    private String code;

    /**
     * Статус выполнения задачи
     */
    @NotNull(message = "Статус инстанса сценария должен быть указан.")
    private String status;

    /**
     * Идентификатор конкретной базы данных
     */
    @NotNull(message = "Id базы данных должен быть указан.")
    @Positive
    private Long databaseId;

    /**
     * Идентификатор запуска
     */
    @NotNull(message = "Id запуска должен быть указан.")
    @Positive
    private Long launchId;

    /**
     * Идентификатор сценария
     */
    @NotNull(message = "Id сценария должен быть указан.")
    @Positive
    private Long scenarioId;

    /**
     * Метка сценария
     */
    private String label;

    /**
     * Дата и время начало выполнения
     */
    @NotNull(message = "Дата начала работы инстанса сценария должна быть указана.")
    @JsonFormat
    private LocalDateTime startDateTime;

    /**
     * Дата и время окончания выполнения
     */
    @JsonFormat
    private LocalDateTime endDateTime;

    /**
     * Входящие данные для начала работы сценария
     */
    private String initContext;
}