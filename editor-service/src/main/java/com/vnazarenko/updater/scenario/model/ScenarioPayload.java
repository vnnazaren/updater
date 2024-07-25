package com.vnazarenko.updater.scenario.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Класс DTO класса "Сценарий" - Scenario
 */
public record ScenarioPayload(

        @Positive
        Long id,

        @NotNull(message = "Id планировщика должен быть указан.")
        @Positive
        Long schedulerId,

        @NotNull(message = "Id базы данных должен быть указан.")
        @Positive
        Long databaseId,

        @NotNull(message = "Id списка задач должен быть указан.")
        @Positive
        Long taskListId,

        String initContext,

        String label
) {
}