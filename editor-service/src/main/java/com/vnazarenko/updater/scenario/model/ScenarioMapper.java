package com.vnazarenko.updater.scenario.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ScenarioMapper {

    @Mappings({@Mapping(target = "scheduler.id", source = "schedulerId"),
            @Mapping(target = "databaseGroup.id", source = "databaseGroupId"),
            @Mapping(target = "taskList.id", source = "taskListId")})
    Scenario toEntity(ScenarioPayload scenarioPayload);

    @Mappings({@Mapping(target = "schedulerId", source = "scheduler.id"),
            @Mapping(target = "databaseGroupId", source = "databaseGroup.id"),
            @Mapping(target = "taskListId", source = "taskList.id")})
    ScenarioPayload toDto(Scenario scenario);

    List<ScenarioPayload> toDtoList(List<Scenario> scenarioList);

    @Mappings({@Mapping(target = "scheduler.id", source = "schedulerId"),
            @Mapping(target = "databaseGroup.id", source = "databaseGroupId"),
            @Mapping(target = "taskList.id", source = "taskListId")})
    Scenario update(ScenarioPayload scenarioPayload, @MappingTarget Scenario scenario);
}