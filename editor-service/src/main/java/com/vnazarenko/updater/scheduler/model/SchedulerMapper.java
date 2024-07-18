package com.vnazarenko.updater.scheduler.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SchedulerMapper {

    @Mapping(target = "isActive", source = "isActive")  // todo - убрать после тестирования
    Scheduler toEntity(SchedulerDto schedulerDto);

    @Mapping(target = "isActive", source = "isActive")  // todo - убрать после тестирования
    SchedulerDto toDto(Scheduler scheduler);

    List<SchedulerDto> toDtoList(List<Scheduler> schedulerList);

    Scheduler update(SchedulerDto schedulerDto, @MappingTarget Scheduler scheduler);
}