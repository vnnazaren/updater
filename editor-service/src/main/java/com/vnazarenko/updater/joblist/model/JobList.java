package com.vnazarenko.updater.joblist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.database.model.Database;
import com.vnazarenko.updater.launch.model.Launch;
import com.vnazarenko.updater.tasklist.model.TaskList;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * Класс JobList описывает настройки экземпляра сценария запущенного в работу
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_lists")
public class JobList {

    /**
     * Системный идентификатор настроек сценария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Имя сценария
     */
    @Column(name = "name")
    private String name;

    /**
     * Статус выполнения задачи
     */
    @Column(name = "status")
    private String status;

    /**
     * Идентификатор конкретной базы данных
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "database_id", referencedColumnName = "id", nullable = false)
    private Database database;

    /**
     * Идентификатор запуска
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "launch_id", referencedColumnName = "id", nullable = false)
    private Launch launch;

    /**
     * Идентификатор списка задач
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_list_id", referencedColumnName = "id", nullable = false)
    private TaskList taskList;

    /**
     * Дата и время начало выполнения
     */
    @Column(name = "start_date_time")
    @JsonFormat
    private LocalDateTime startDateTime;

    /**
     * Дата и время окончания выполнения
     */
    @Column(name = "end_date_time")
    @JsonFormat
    private LocalDateTime endDateTime;

    /**
     * Входящие данные для начала работы сценария
     */
    @Column(name = "init_context")
    private String initContext;
}