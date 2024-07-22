package com.vnazarenko.updater.scheduler;

import com.vnazarenko.updater.scheduler.model.SchedulerPayload;

import java.util.List;

public interface SchedulerService {

    SchedulerPayload createScheduler(SchedulerPayload schedulerPayload);

    SchedulerPayload readScheduler(Long id);

    List<SchedulerPayload> readSchedulers();

    SchedulerPayload updateScheduler(Long id, SchedulerPayload schedulerPayload);

    void deleteScheduler(Long id);
}