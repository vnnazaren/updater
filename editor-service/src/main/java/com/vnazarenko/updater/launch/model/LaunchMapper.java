package com.vnazarenko.updater.launch.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface LaunchMapper {

    @Mapping(target = "scheduler.id", source = "schedulerId")
    Launch toEntity(LaunchPayload launchPayload);

    @Mapping(target = "schedulerId", source = "scheduler.id")
    LaunchPayload toDto(Launch launch);

    List<LaunchPayload> toDtoList(List<Launch> launchList);

    @Mapping(target = "scheduler.id", source = "schedulerId")
    Launch update(LaunchPayload LaunchListDto, @MappingTarget Launch Launch);
}