package com.vnazarenko.updater.launch.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LaunchMapper {

    Launch toEntity(LaunchPayload launchPayload);

    LaunchPayload toDto(Launch launch);

    List<LaunchPayload> toDtoList(List<Launch> launchList);

    Launch update(LaunchPayload LaunchListDto, @MappingTarget Launch Launch);
}