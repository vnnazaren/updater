package com.vnazarenko.updater.scenario.model;

import com.vnazarenko.updater.database_group.model.DatabaseGroup;
import com.vnazarenko.updater.scheduler.model.Scheduler;
import com.vnazarenko.updater.task_list.model.TaskList;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Класс Scenario описывает настройки запуска сценария на конкретной БД
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scenarios")
public class Scenario {

    /**
     * Системный идентификатор настроек сценария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Идентификатор планировщика
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "scheduler_id", referencedColumnName = "id", nullable = false)
    private Scheduler scheduler;

    /**
     * Идентификатор конкретной базы данных
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "database_group_id", referencedColumnName = "id", nullable = false)
    private DatabaseGroup databaseGroup;

    /**
     * Идентификатор сценария
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_list_id", referencedColumnName = "id", nullable = false)
    private TaskList taskList;

    /**
     * Входящие данные для начала работы сценария
     */
    @Column(name = "init_context")
    private String initContext;

    /**
     * Метка для связи двух разных биллингов работающих вместе
     */
    @Column(name = "label")
    private String label;
}