package com.vnazarenko.updater.tasklist.model;

import com.vnazarenko.updater.database_group.model.DatabaseGroup;
import com.vnazarenko.updater.util.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Класс TaskList описывает настройки сценария
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_lists")
public class TaskList {

    /**
     * Системный идентификатор сценария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Пользовательское имя сценария
     */
    @Column(name = "name")
    private String name;

    /**
     * Статус сценария
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    /**
     * Описание сценария
     */
    @Column(name = "description")
    private String description;

    /**
     * Коллекция с базами данных входящих в подборку
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_list_groups_links",
            joinColumns = @JoinColumn(name = "task_list_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "database_group_id", referencedColumnName = "id"))
    private Set<DatabaseGroup> databaseGroups;
}