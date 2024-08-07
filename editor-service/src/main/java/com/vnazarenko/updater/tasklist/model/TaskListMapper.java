package com.vnazarenko.updater.tasklist.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TaskListMapper {

    TaskList toEntity(TaskListPayload taskListDto);

    TaskListPayload toDto(TaskList scenario);

    List<TaskListPayload> toDtoList(List<TaskList> taskLists);

    TaskList update(TaskListPayload taskListDto, @MappingTarget TaskList taskList);
}