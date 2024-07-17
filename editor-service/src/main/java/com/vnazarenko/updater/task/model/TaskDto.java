package com.vnazarenko.updater.task.model;

import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Экземпляр Задачи планируемой в рамках Сценария.<br/>
 * На основе этого класса, при запуске в работу, формируется объект класса TaskInstanceDto,
 * который будет существовать в рамках ScenarioInstanceDto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    /**
     * Системный идентификатор задачи
     */
    @Positive
    private Long id;

    /**
     * Имя задачи
     */
    private String name;

    /**
     * Сценарий, которому принадлежит задача
     */
    private Long taskListId;

    /**
     * Язык скрипта задачи (PL/SQL, Lua, Shell и т. д.)
     */
    private ActionType actionType;

    /**
     * Исходный код выполняемый в рамках текущей задачи
     */
    private String actionScript;

    /**
     * Ожидаемый результат для проверки корректного выполнения задачи
     */
    private String expectedResult;

    /**
     * Список флагов, которые влияют на логику обработки задачи
     */
    private String flags;

    /**
     * Статус задачи
     */
    private StatusType status;

    /**
     * Описание задачи
     */
    private String description;

    /**
     * Ссылка на предшественников
     */
    private Set<Long> ancestors;
}