package com.vnazarenko.updater.task_list;

import com.vnazarenko.updater.task_list.model.TaskListPayload;

import java.util.List;

public interface TaskListService {

    TaskListPayload createTaskList(TaskListPayload taskListDto);

    TaskListPayload readTaskList(Long id);

    List<TaskListPayload> readTaskLists();

    TaskListPayload updateTaskList(Long id, TaskListPayload taskListDto);

    void deleteTaskList(Long id);
}