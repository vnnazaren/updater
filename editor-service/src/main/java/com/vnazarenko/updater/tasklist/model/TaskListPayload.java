package com.vnazarenko.updater.tasklist.model;

import com.vnazarenko.updater.util.Marker;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Класс DTO класса "TaskList"
 */
public record TaskListPayload(@NotNull(groups = {Marker.OnUpdate.class})
                              Long id,
                              @NotBlank(groups = {Marker.OnCreate.class},
                                      message = "Имя сценария должно быть указано.")
                              @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                      min = 1, max = 255,
                                      message = "Длина имени списка задач должно быть от 1 до 255 символов.")
                              String name,
                              @NotNull(groups = {Marker.OnCreate.class},
                                      message = "Статус сценария должен быть указан.")
                              StatusType status,
                              @NotBlank(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                      message = "Описание сценария должно быть указано.")
                              @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                      min = 10, max = 4000,
                                      message = "Длина имени списка задач должно быть от 10 до 4000 символов.")
                              String description) {
}