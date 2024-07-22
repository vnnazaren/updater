package com.vnazarenko.updater.tasklist.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskListMapper {

    TaskList toEntity(TaskListPayload taskListDto);

    TaskListPayload toDto(TaskList scenario);

    List<TaskListPayload> toDtoList(List<TaskList> taskLists);

    TaskList update(TaskListPayload taskListDto, @MappingTarget TaskList taskList);
}