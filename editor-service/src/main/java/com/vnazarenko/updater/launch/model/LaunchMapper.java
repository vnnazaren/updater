package com.vnazarenko.updater.launch.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LaunchMapper {

    Launch toEntity(LaunchDto launchDto);

    LaunchDto toDto(Launch launch);

    List<LaunchDto> toDtoList(List<Launch> launchList);

    Launch update(LaunchDto LaunchListDto, @MappingTarget Launch Launch);
}