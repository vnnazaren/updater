package com.vnazarenko.updater.task;

import com.vnazarenko.updater.exception.BadRequestException;
import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.task.model.Task;
import com.vnazarenko.updater.task.model.TaskDto;
import com.vnazarenko.updater.task.model.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {
    private final TaskStorage dao;
    private final TaskMapper mapper;

    @Override
    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapper.toEntity(taskDto);

        Set<Long> ancestors = taskDto.getAncestors();
        if (ancestors != null) {
            task.setAncestors(this.readTasksByIdIn(ancestors));
        }

        try {
            return mapper.toDto(dao.save(task));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(
                    "Ошибка при создании задачи: %s".formatted(taskDto));
        }
    }

    @Override
    public TaskDto readTask(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Task with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<TaskDto> readTasks() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    public Set<Task> readTasksByIdIn(Set<Long> tasks) {
        return dao.findAllByIdIn(tasks);
    }

    @Override
    @Transactional
    public TaskDto updateTask(Long id, TaskDto taskDto) {

        Task task = mapper.toEntity(this.readTask(id));
        Task updatedTask = mapper.update(taskDto, task);

        Set<Long> ancestors = taskDto.getAncestors();
        if (ancestors != null) {
            updatedTask.setAncestors(this.readTasksByIdIn(ancestors));
        }

        try {
            return mapper.toDto(dao.save(updatedTask));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("Error updating task with ID %s.".formatted(task.getId()));
        }
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        dao.deleteById(id);
    }
}