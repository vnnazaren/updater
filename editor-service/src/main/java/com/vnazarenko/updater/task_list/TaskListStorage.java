package com.vnazarenko.updater.task_list;

import com.vnazarenko.updater.task_list.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListStorage extends JpaRepository<TaskList, Long> {

}