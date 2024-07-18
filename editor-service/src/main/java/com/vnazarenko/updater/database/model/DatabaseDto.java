package com.vnazarenko.updater.database.model;

import com.vnazarenko.updater.util.Marker;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс DTO класса "База данных" - DATABASE
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseDto {

    /**
     * Системный идентификатор
     */
//    @NotNull(groups = {Marker.OnUpdate.class})
    @Positive
    private Long id;

    /**
     * Пользовательское имя базы данных
     */
    @NotBlank(groups = {Marker.OnCreate.class},
            message = "Имя БД должно быть указано.")
    @Size(min = 1, max = 255, message = "Длина имени базы данных должно быть от 1 до 255 символов.")
    private String name;

    /**
     * Адрес базы данных
     */
    @NotBlank(groups = {Marker.OnCreate.class},
            message = "Адрес базы данных должен быть указан.")
    @Size(min = 1, max = 255, message = "Длина адреса базы данных должно быть от 1 до 255 символов.")
    private String url;

    /**
     * Порт базы данных
     */
    @NotNull(groups = {Marker.OnCreate.class},
            message = "Порт базы данных должен быть указан.")
    @Min(groups = {Marker.OnCreate.class, Marker.OnUpdate.class}, value = 0, message = "Порт базы данных должен быть положительным числом.")
    @Max(groups = {Marker.OnCreate.class, Marker.OnUpdate.class}, value = 65536, message = "Порт базы данных может быть максимум 65536")
    private Integer port;   // todo - ОШИБКА - почему то минимум и максимум не работают

    /**
     * Логин тех. пользователя
     */
    @NotBlank(groups = {Marker.OnCreate.class},
            message = "Логин тех. пользователя базы данных должен быть указан.")
    @Size(min = 1, max = 255, message = "Длина логина тех. пользователя базы данных должно быть от 1 до 255 символов.")
    private String login;

    /**
     * Пароль тех. пользователя
     */
    @NotBlank(groups = {Marker.OnCreate.class},
            message = "Пароль тех. пользователя базы данных должен быть указан.")
    @Size(min = 1, max = 255, message = "Длина пароля базы данных должно быть от 1 до 255 символов.")
    private String password;

    /**
     * Признак "боевой" базы данных
     */
    @NotNull(groups = {Marker.OnCreate.class},
            message = "Признак базы данных должен быть указан.")
    private Boolean isProd;
}