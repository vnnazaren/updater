package com.vnazarenko.updater.tasklist;

import com.vnazarenko.updater.tasklist.model.TaskListDto;

import java.util.List;

public interface TaskListService {

    TaskListDto createTaskList(TaskListDto taskListDto);

    TaskListDto readTaskList(Long id);

    List<TaskListDto> readTaskLists();

    TaskListDto updateTaskList(Long id, TaskListDto taskListDto);

    void deleteTaskList(Long id);
}