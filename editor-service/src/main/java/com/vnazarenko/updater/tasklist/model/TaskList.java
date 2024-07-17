package com.vnazarenko.updater.tasklist.model;

import com.vnazarenko.updater.util.StatusType;
import jakarta.persistence.*;
import lombok.*;

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
     * Теги баз данных совместимых со сценарием
     */
    @Column(name = "db_tags")
    private String dbTags;

    /**
     * Описание сценария
     */
    @Column(name = "description")
    private String description;
}