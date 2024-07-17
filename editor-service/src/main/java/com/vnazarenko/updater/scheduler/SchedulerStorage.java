package com.vnazarenko.updater.scheduler;

import com.vnazarenko.updater.scheduler.model.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerStorage extends JpaRepository<Scheduler, Long> {

}