package com.vnazarenko.updater.tasklist.model;

import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Класс DTO класса "Сценарий" - TaskList
 */

public record TaskListPayload(

        @Positive
        Long id,

        @NotBlank(message = "Имя сценария должно быть указано.")
        String name,

        @NotNull(message = "Статус сценария должен быть указан.")
        StatusType status,

        @NotBlank(message = "Описание сценария должно быть указано.")
        String description
) {
}