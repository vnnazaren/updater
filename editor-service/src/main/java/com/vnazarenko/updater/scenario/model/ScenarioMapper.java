package com.vnazarenko.updater.scenario.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScenarioMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "scheduler.id", source = "schedulerId"),
            @Mapping(target = "database.id", source = "databaseId"),
            @Mapping(target = "taskList.id", source = "taskListId")
    })
    Scenario toEntity(ScenarioDto scenarioSettingsDto);

    @Mappings({
            @Mapping(target = "schedulerId", source = "scheduler.id"),
            @Mapping(target = "databaseId", source = "database.id"),
            @Mapping(target = "taskListId", source = "taskList.id")
    })
    ScenarioDto toDto(Scenario scenarioSettings);

    List<ScenarioDto> toDtoList(List<Scenario> scenarioSettingsList);

    Scenario update(ScenarioDto scenarioDto, @MappingTarget Scenario scenario);
}