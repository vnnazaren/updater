package com.vnazarenko.updater.database_group.model;

import com.vnazarenko.updater.util.Marker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Класс DTO класса "Database"
 */
public record DatabaseGroupPayload(@NotNull(groups = {Marker.OnUpdate.class})
                                   @Positive
                                   Long id,
                                   @NotBlank(groups = {Marker.OnCreate.class},
                                           message = "Имя группы баз данных должно быть указано.")
                                   @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                           min = 1, max = 255,
                                           message = "Длина имени группы базы данных должна быть от 1 до 255 символов.")
                                   String name,
                                   @Size(groups = {Marker.OnCreate.class, Marker.OnUpdate.class},
                                           min = 10, max = 4000,
                                           message = "Длина описания группы баз данных должна быть от 10 до 4000 символов.")
                                   String description) {
}