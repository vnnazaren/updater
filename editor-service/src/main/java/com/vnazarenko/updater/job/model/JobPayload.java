package com.vnazarenko.updater.job.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vnazarenko.updater.util.ActionType;
import com.vnazarenko.updater.util.StatusType;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Set;

import static com.vnazarenko.updater.util.Const.DATE_TIME_FORMAT;

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
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
                         LocalDateTime startDateTime,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
                         LocalDateTime endDateTime,
                         String incomingContext,
                         String context,
                         String infoMsg,
                         Set<Long> ancestors) {
}