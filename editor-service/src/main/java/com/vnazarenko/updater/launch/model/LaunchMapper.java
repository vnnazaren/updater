package com.vnazarenko.updater.launch.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LaunchMapper {

    @Mapping(target = "scheduler.id", source = "schedulerId")
    Launch toEntity(LaunchPayload launchPayload);

    @Mapping(target = "schedulerId", source = "scheduler.id")
    LaunchPayload toDto(Launch launch);

    List<LaunchPayload> toDtoList(List<Launch> launchList);

    @Mapping(target = "scheduler.id", source = "schedulerId")
    Launch update(LaunchPayload LaunchListDto, @MappingTarget Launch Launch);
}