package com.vnazarenko.updater.task;

import com.vnazarenko.updater.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TaskStorage extends JpaRepository<Task, Long> {

    Set<Task> findAllByIdIn(Set<Long> tasks);
}