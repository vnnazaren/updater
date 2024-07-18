package com.vnazarenko.updater.scenario.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс DTO класса "Настройки Сценария" - Scenario
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioDto {

    /**
     * Системный идентификатор настроек сценария
     */
    @Positive
    private Long id;

    /**
     * Идентификатор планировщика
     */
    @NotNull(message = "Id планировщика должен быть указан.")
    @Positive
    private Long schedulerId;

    /**
     * Идентификатор конкретной базы данных
     */
    @NotNull(message = "Id базы данных должен быть указан.")
    @Positive
    private Long databaseId;

    /**
     * Идентификатор сценария
     */
    @NotNull(message = "Id списка задач должен быть указан.")
    @Positive
    private Long taskListId;

    /**
     * Входящие данные для начала работы сценария
     */
    private String initContext;

    /**
     * Метка для связи двух разных биллингов работающих вместе
     */
    private String label;
}