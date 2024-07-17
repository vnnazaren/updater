package com.vnazarenko.updater.scheduler.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchedulerMapper {

    @Mappings({@Mapping(target = "id", ignore = true),
            @Mapping(target = "isActive", source = "isActive")})
    Scheduler toEntity(SchedulerDto schedulerDto);

    @Mapping(target = "isActive", source = "isActive")
    SchedulerDto toDto(Scheduler scheduler);

    List<SchedulerDto> toDtoList(List<Scheduler> schedulerList);

    Scheduler update(SchedulerDto schedulerDto, @MappingTarget Scheduler scheduler);
}