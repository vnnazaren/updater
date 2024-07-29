package com.vnazarenko.updater.job.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Класс DTO класса "Job"
 */
public record JobPayload(@Positive
                         Long id,
                         String name,
                         Long jobListId,
                         ActionType actionType,
                         String actionScript,
                         String expectedResult,
                         String flags,
                         StatusType status,
                         String description,
                         String dbProcId,
                         @JsonFormat
                         LocalDateTime startDateTime,
                         @JsonFormat
                         LocalDateTime endDateTime,
                         String incomingContext,
                         String context,
                         String infoMsg,
                         Set<Long> ancestors) {
}