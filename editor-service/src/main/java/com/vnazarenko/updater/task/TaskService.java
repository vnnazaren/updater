package com.vnazarenko.updater.task;

import com.vnazarenko.updater.task.model.Task;
import com.vnazarenko.updater.task.model.TaskPayload;

import java.util.List;
import java.util.Set;

// todo - особо сильно надо протестировать поведение/наличие/отсутствие предков у задачи
public interface TaskService {

    TaskPayload createTask(TaskPayload taskPayload);

    TaskPayload readTask(Long id);

    List<TaskPayload> readTasks();

    Set<Task> readTasksByIdIn(Set<Long> tasks);

    TaskPayload updateTask(Long id, TaskPayload taskPayload);

    void deleteTask(Long id);
}