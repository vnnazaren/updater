package com.vnazarenko.updater.tasklist;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.tasklist.model.TaskList;
import com.vnazarenko.updater.tasklist.model.TaskListDto;
import com.vnazarenko.updater.tasklist.model.TaskListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class TaskListServiceImpl implements TaskListService {
    private final TaskListStorage dao;
    private final TaskListMapper mapper;

    @Override
    @Transactional
    public TaskListDto createTaskList(TaskListDto taskListDto) {
        return mapper.toDto(dao.save(mapper.toEntity(taskListDto)));
    }

    @Override
    public TaskListDto readTaskList(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("TaskList with id \"%d\" not found.".formatted(id))));
    }

    @Override
    public List<TaskListDto> readTaskLists() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public TaskListDto updateTaskList(Long id, TaskListDto taskListDto) {
        TaskList taskList = mapper.toEntity(this.readTaskList(id));
        TaskList updatedTaskList = mapper.update(taskListDto, taskList);

        return mapper.toDto(dao.save(updatedTaskList));
    }

    @Override
    @Transactional
    public void deleteTaskList(Long id) {
        dao.deleteById(id);
    }
}