package com.vnazarenko.updater.task.model;

import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Positive;

import java.util.Set;

/**
 * Класс DTO класса "Task"
 */
public record TaskPayload(@Positive Long id,
                          String name,
                          Long taskListId,
                          ActionType actionType,
                          String actionScript,
                          String expectedResult,
                          String flags,
                          StatusType status,
                          String description,
                          Set<Long> ancestors) {
}