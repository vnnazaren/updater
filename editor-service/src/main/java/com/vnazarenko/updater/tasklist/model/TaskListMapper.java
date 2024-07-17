package com.vnazarenko.updater.tasklist.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper {

    @Mapping(target = "id", ignore = true)
    TaskList toEntity(TaskListDto scenarioDto);

    TaskListDto toDto(TaskList scenario);

    List<TaskListDto> toDtoList(List<TaskList> scenarioList);

    TaskList update(TaskListDto taskListDto, @MappingTarget TaskList taskList);
}