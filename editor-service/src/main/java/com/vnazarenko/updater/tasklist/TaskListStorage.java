package com.vnazarenko.updater.tasklist;

import com.vnazarenko.updater.tasklist.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListStorage extends JpaRepository<TaskList, Long> {

}