package com.vnazarenko.updater.databasegroup.model;

import com.vnazarenko.updater.util.Marker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Класс DTO класса "Database"
 */
public record DatabaseGroupPayload(@NotNull(groups = {Marker.OnUpdate.class})
                                   @Positive Long id,
                                   @NotBlank(groups = {Marker.OnCreate.class}, message = "Имя группы баз данных должно быть указано.")
                                   @Size(min = 1, max = 255, message = "Длина имени группы базы данных должна быть от 1 до 255 символов.")
                                   String name,
                                   @Size(min = 1, max = 4000, message = "Длина описания группы баз данных должна быть от 1 до 4000 символов.")
                                   String description) {
}