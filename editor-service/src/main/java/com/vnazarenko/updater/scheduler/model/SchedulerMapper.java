package com.vnazarenko.updater.scheduler.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SchedulerMapper {

    @Mapping(target = "isActive", source = "isActive")
        // todo - убрать после тестирования
    Scheduler toEntity(SchedulerPayload schedulerPayload);

    @Mapping(target = "isActive", source = "isActive")
        // todo - убрать после тестирования
    SchedulerPayload toDto(Scheduler scheduler);

    List<SchedulerPayload> toDtoList(List<Scheduler> schedulerList);

    Scheduler update(SchedulerPayload schedulerPayload, @MappingTarget Scheduler scheduler);
}