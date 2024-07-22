package com.vnazarenko.updater.scheduler.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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