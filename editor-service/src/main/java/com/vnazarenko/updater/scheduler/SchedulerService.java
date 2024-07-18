package com.vnazarenko.updater.scheduler;

import com.vnazarenko.updater.scheduler.model.SchedulerDto;

import java.util.List;

public interface SchedulerService {

    SchedulerDto createScheduler(SchedulerDto schedulerDto);

    SchedulerDto readScheduler(Long id);

    List<SchedulerDto> readSchedulers();

    SchedulerDto updateScheduler(Long id, SchedulerDto schedulerDto);

    void deleteScheduler(Long id);
}