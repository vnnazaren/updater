package com.vnazarenko.updater.task.model;

import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Positive;

import java.util.Set;

/**
 * Экземпляр Задачи планируемой в рамках Сценария.<br/>
 * На основе этого класса, при запуске в работу, формируется объект класса TaskInstanceDto,
 * который будет существовать в рамках ScenarioInstanceDto.
 */
public record TaskPayload(

        @Positive
        Long id,

        String name,

        Long taskListId,

        ActionType actionType,

        String actionScript,

        String expectedResult,

        String flags,

        StatusType status,

        String description,

        Set<Long> ancestors
) {
}