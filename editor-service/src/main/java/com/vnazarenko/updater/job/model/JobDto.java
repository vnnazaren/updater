package com.vnazarenko.updater.job.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Экземпляр Задачи запущенной в работу.<br/>
 * Выполняется в конкретное время, на конкретной базе, в рамках конкретного JobListDto,
 * созданного на основе конкретного Сценария.<br/>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    /**
     * Системный идентификатор задачи
     */
    @Positive
    private Long id;

    /**
     * Имя задачи
     */
    private String name;

    /**
     * Указатель на сценарий в котором будет выполняться задача
     */
    private Long jobListId;

    /**
     * Язык скрипта (PL/SQL, Lua, Shell и т. д.)
     */
    private ActionType actionType;

    /**
     * Исходный код выполняемый в рамках текущей задачи
     */
    private String actionScript;

    /**
     * Ожидаемый результат для проверки корректного выполнения задачи
     */
    private String expectedResult;

    /**
     * Список флагов, которые влияют на логику обработки задачи
     */
    private String flags;

    /**
     * Статус выполнения задачи
     */
    private StatusType status;

    /**
     * Описание задачи
     */
    private String description;

    /**
     * Идентификатор процесса Oracle запущенного этой задачей
     */
    private String dbProcId;

    /**
     * Дата и время начало выполнения
     */
    @JsonFormat
    private LocalDateTime startDateTime;

    /**
     * Дата и время окончания выполнения
     */
    @JsonFormat
    private LocalDateTime endDateTime;

    /**
     * Входящий контекст из настроек сценария или от предков
     */
    private String incomingContext;

    /**
     * Контекст задачи для потомков
     */
    private String context;

    /**
     * Информационное сообщение о ходе (или результате) выполнения задачи
     */
    private String infoMsg;

    /**
     * Ссылка на предшественников
     */
    private Set<Long> ancestors;
}