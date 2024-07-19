package com.vnazarenko.updater.tasklist.model;

import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс DTO класса "Сценарий" - TaskList
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDto {

    /**
     * Системный идентификатор сценария
     */
    @Positive
    private Long id;

    /**
     * Пользовательское имя сценария
     */
    @NotBlank(message = "Имя сценария должно быть указано.")
    private String name;

    /**
     * Статус сценария
     */
    @NotNull(message = "Статус сценария должен быть указан.")
    private StatusType status;

    /**
     * Описание сценария
     */
    @NotBlank(message = "Описание сценария должно быть указано.")
    private String description;
}