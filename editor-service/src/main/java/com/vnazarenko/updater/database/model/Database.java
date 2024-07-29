package com.vnazarenko.updater.database.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс Database описывает настройки клиентской базы данных
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "databases")
public class Database {     // todo - где-то нужно реализовать проверку соединения с БД

    /**
     * Системный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Пользовательское имя базы данных
     */
    @Column(name = "name")
    private String name;

    /**
     * Адрес базы данных
     */
    @Column(name = "url")
    private String url;

    /**
     * Порт базы данных
     */
    @Column(name = "port")
    private Integer port;

    /**
     * Логин тех. пользователя
     */
    @Column(name = "login")
    private String login;

    /**
     * Пароль тех. пользователя
     */
    @Column(name = "password")
    private String password;

    /**
     * Признак "боевой" базы данных
     */
    @Column(name = "is_prod")
    private Boolean isProd;
}