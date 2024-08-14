package com.vnazarenko.updater.task.model;

import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.Marker;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

/**
 * Класс DTO класса "Task"
 */
public record TaskPayload(@NotNull(groups = {Marker.OnUpdate.class})
                          Long id,
                          @Size(min = 1, max = 255,
                                  message = "Длина имени базы данных должна быть от 1 до 255 символов.")
                          String name,
                          @Min(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                  value = 1,
                                  message = "Номер списка задач должен быть положительным числом.")
                          Long taskListId,
                          ActionType actionType,
                          @NotBlank(groups = {Marker.OnCreate.class},
                                  message = "Скрипт должен быть указан.")
                          @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                  min = 1, max = 4000,
                                  message = "Длина скрипта должна быть от 1 до 1000 символов.")
                          String actionScript,
                          @NotBlank(groups = {Marker.OnCreate.class},
                                  message = "Ожидаемый результат выполнения задачи должен быть указан.")
                          @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                  min = 1, max = 1000,
                                  message = "Длина ожидаемого результат должна быть от 1 до 1000 символов.")
                          String expectedResult,
                          String flags,
                          StatusType status,
                          @NotBlank(groups = {Marker.OnCreate.class},
                                  message = "Описание задачи должно быть указано.")
                          @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                  min = 1, max = 4000,
                                  message = "Длина описания должна быть от 1 до 4000 символов.")
                          String description,
                          Set<Long> ancestors) {
}