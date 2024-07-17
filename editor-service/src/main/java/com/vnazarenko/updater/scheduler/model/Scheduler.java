package com.vnazarenko.updater.scheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Класс Scheduler описывает настройки планировщика
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedulers")
public class Scheduler {

    /**
     * Системный идентификатор настроек планировщика
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Имя планировщика
     */
    @Column(name = "name")
    private String name;

    /**
     * Запланированное время старта
     */
    @Column(name = "schedule_to_start")
    @JsonFormat
    private LocalDateTime scheduleToStart;

    /**
     * Взведён ли планировщик
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Описание планировщика
     */
    @Column(name = "description")
    private String description;
}