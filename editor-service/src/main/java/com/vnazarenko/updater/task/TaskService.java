package com.vnazarenko.updater.task;

import com.vnazarenko.updater.task.model.Task;
import com.vnazarenko.updater.task.model.TaskDto;

import java.util.List;
import java.util.Set;

// todo - особо сильно надо протестировать поведение/наличие/отсутствие предков у задачи
public interface TaskService {

    TaskDto createTask(TaskDto taskDto);

    TaskDto readTask(Long id);

    List<TaskDto> readTasks();

    Set<Task> readTasksByIdIn(Set<Long> tasks);

    TaskDto updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);
}