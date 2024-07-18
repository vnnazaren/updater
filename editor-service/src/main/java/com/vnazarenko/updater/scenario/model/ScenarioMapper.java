package com.vnazarenko.updater.scenario.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ScenarioMapper {

    @Mappings({
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