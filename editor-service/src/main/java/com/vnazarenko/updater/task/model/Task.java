package com.vnazarenko.updater.task.model;

import com.vnazarenko.updater.task_list.model.TaskList;
import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

/**
 * Класс Task описывает настройки задачи планируемой в рамках TaskList
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

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
    @JoinColumn(name = "task_list_id", referencedColumnName = "id", nullable = false)
    private TaskList taskList;

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
     * Статус задачи
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusType status;

    /**
     * Описание задачи
     */
    @Column(name = "description")
    private String description;

    /**
     * Ссылка на предшественников
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "ancestor_task_links",
            joinColumns = @JoinColumn(name = "descendent_task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ancestor_task_id", referencedColumnName = "id"))
    private Set<Task> ancestors;
}