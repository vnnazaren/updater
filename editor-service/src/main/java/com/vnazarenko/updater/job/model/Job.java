package com.vnazarenko.updater.job.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.joblist.model.JobList;
import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Класс Job описывает настройки задачи запущенной в работу
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {

    /**
     * Системный идентификатор задачи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Имя задачи
     */
    @Column(name = "name")
    private String name;

    /**
     * Сценарий, которому принадлежит задача
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "job_list_id", referencedColumnName = "id", nullable = false)
    private JobList jobList;

    /**
     * Язык скрипта (PL/SQL, Lua, Shell и т. д.)
     */
    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    /**
     * Исходный код выполняемый в рамках текущей задачи
     */
    @Column(name = "action_script")
    private String actionScript;

    /**
     * Ожидаемый результат для проверки корректного выполнения задачи
     */
    @Column(name = "expected_result")
    private String expectedResult;

    /**
     * Список флагов, которые влияют на логику обработки задачи
     */
    @Column(name = "flags")
    private String flags;

    /**
     * Статус выполнения задачи
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    /**
     * Описание задачи. Не используется в логике работы
     */
    @Column(name = "description")
    private String description;

    /**
     * Идентификатор процесса Oracle запущенного этой задачей
     */
    @Column(name = "db_proc_id")
    private String dbProcId;

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
     * Входящий контекст из настроек сценария или от предков
     */
    @Column(name = "incoming_context")
    private String incomingContext;

    /**
     * Контекст задачи для потомков
     */
    @Column(name = "context")
    private String context;

    /**
     * Информационное сообщение о ходе (или результате) выполнения задачи
     */
    @Column(name = "info_msg")
    private String infoMsg;

    /**
     * Ссылка на предшественников
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ancestor_job_links",
            joinColumns = @JoinColumn(name = "descendent_job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ancestor_job_id", referencedColumnName = "id"))
    private Set<Job> ancestors;
}