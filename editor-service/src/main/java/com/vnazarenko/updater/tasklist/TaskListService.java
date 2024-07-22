package com.vnazarenko.updater.tasklist;

import com.vnazarenko.updater.tasklist.model.TaskListPayload;

import java.util.List;

public interface TaskListService {

    TaskListPayload createTaskList(TaskListPayload taskListDto);

    TaskListPayload readTaskList(Long id);

    List<TaskListPayload> readTaskLists();

    TaskListPayload updateTaskList(Long id, TaskListPayload taskListDto);

    void deleteTaskList(Long id);
}