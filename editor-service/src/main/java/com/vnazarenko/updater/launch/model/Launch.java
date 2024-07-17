package com.vnazarenko.updater.launch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.scheduler.model.Scheduler;
import com.vnazarenko.updater.util.StatusType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

/**
 * Класс Launch описывает настройки запуска
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "launches")
public class Launch {

    /**
     * Системный идентификатор экземпляра запуска
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Id идентификатора расписания запуска
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "scheduler_id", referencedColumnName = "id", nullable = false)
    private Scheduler scheduler;

    /**
     * Статус выполнения задачи
     */
    @Column(name = "status")
    private StatusType status;

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
}