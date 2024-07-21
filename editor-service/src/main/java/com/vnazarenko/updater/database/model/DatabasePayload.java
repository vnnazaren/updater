package com.vnazarenko.updater.database.model;

import com.vnazarenko.updater.util.Marker;
import jakarta.validation.constraints.*;

public record DatabasePayload(
        @NotNull(groups = {Marker.OnUpdate.class})
        @Positive Long id,
        String name,

        @NotBlank(groups = {Marker.OnCreate.class},
                message = "Адрес базы данных должен быть указан.")
        @Size(min = 1, max = 255, message = "Длина адреса базы данных должно быть от 1 до 255 символов.")
        String url,

        @NotNull(groups = {Marker.OnCreate.class},
                message = "Порт базы данных должен быть указан.")
        @Min(groups = {Marker.OnCreate.class, Marker.OnUpdate.class}, value = 0, message = "Порт базы данных должен быть положительным числом.")
        @Max(groups = {Marker.OnCreate.class, Marker.OnUpdate.class}, value = 65536, message = "Порт базы данных может быть максимум 65536")
        Integer port,   // todo - ОШИБКА - почему то минимум и максимум не работают

        @NotBlank(groups = {Marker.OnCreate.class},
                message = "Логин тех. пользователя базы данных должен быть указан.")
        @Size(min = 1, max = 255, message = "Длина логина тех. пользователя базы данных должно быть от 1 до 255 символов.")
        String login,

        @NotBlank(groups = {Marker.OnCreate.class},
                message = "Пароль тех. пользователя базы данных должен быть указан.")
        @Size(min = 1, max = 255, message = "Длина пароля базы данных должно быть от 1 до 255 символов.")
        String password,

        @NotNull(groups = {Marker.OnCreate.class},
                message = "Признак базы данных должен быть указан.")
        Boolean isProd) {
}