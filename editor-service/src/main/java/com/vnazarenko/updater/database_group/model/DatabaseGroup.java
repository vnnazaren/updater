package com.vnazarenko.updater.database_group.model;

import com.vnazarenko.updater.database.model.Database;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Класс Database описывает настройки клиентской базы данных
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "database_groups")
public class DatabaseGroup {

    /**
     * Системный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Пользовательское имя группы баз данных
     */
    @Column(name = "name")
    private String name;

    /**
     * Описание группы баз данных
     */
    @Column(name = "description")
    private String description;

    /**
     * Коллекция с базами данных входящих в подборку
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "database_groups_links",
            joinColumns = @JoinColumn(name = "database_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "database_id", referencedColumnName = "id"))
    private Set<Database> databases;
}