package com.vnazarenko.updater.task.model;

import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {

    @Named("taskSetToIdSet")
    default Set<Long> mapToIdSet(Set<Task> ancestors) {
        if (ancestors == null) {
            return Collections.emptySet();
        }

        return ancestors.stream()
                .map(Task::getId)
                .collect(Collectors.toSet());
    }

    @Named("idSetToTaskSet")   // todo - возможно это не нужно - просто сделать "ignore"
    default Set<Task> mapToTaskSet(Set<Long> ancestors) {
        if (ancestors == null) {
            return Collections.emptySet();
        }
        return ancestors.stream()
                .map(taskId -> Task.builder().id(taskId).build())
                .collect(Collectors.toSet());
    }

    @Mappings({@Mapping(target = "taskList.id", source = "taskListId"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "idSetToTaskSet")})
    Task toEntity(TaskPayload taskPayload);

    @Mappings({@Mapping(target = "taskListId", source = "taskList.id"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "taskSetToIdSet")})
    TaskPayload toDto(Task task);

    List<TaskPayload> toDtoList(List<Task> taskList);

    @Mappings({@Mapping(target = "taskList.id", source = "taskListId"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "idSetToTaskSet")})
    Task update(TaskPayload taskPayload, @MappingTarget Task task);
}