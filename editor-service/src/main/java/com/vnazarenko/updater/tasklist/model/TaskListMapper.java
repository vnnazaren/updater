package com.vnazarenko.updater.tasklist.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskListMapper {

    TaskList toEntity(TaskListDto scenarioDto);

    TaskListDto toDto(TaskList scenario);

    List<TaskListDto> toDtoList(List<TaskList> scenarioList);

    TaskList update(TaskListDto taskListDto, @MappingTarget TaskList taskList);
}