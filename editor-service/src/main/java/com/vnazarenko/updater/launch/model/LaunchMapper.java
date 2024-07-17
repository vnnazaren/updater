package com.vnazarenko.updater.launch.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LaunchMapper {

    @Mapping(target = "id", ignore = true)
    Launch toEntity(LaunchDto launchDto);

    LaunchDto toDto(Launch launch);

    List<LaunchDto> toDtoList(List<Launch> launchList);

    Launch update(LaunchDto LaunchListDto, @MappingTarget Launch Launch);
}