package com.vnazarenko.updater.scheduler;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.scheduler.model.Scheduler;
import com.vnazarenko.updater.scheduler.model.SchedulerMapper;
import com.vnazarenko.updater.scheduler.model.SchedulerPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class SchedulerServiceImpl implements SchedulerService {
    private final SchedulerStorage dao;
    private final SchedulerMapper mapper;

    @Override
    @Transactional
    public SchedulerPayload createScheduler(SchedulerPayload schedulerPayload) {
        return mapper.toDto(dao.save(mapper.toEntity(schedulerPayload)));
    }

    @Override
    public SchedulerPayload readScheduler(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Scheduler with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<SchedulerPayload> readSchedulers() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public SchedulerPayload updateScheduler(Long id, SchedulerPayload schedulerPayload) {
        Scheduler scheduler = mapper.toEntity(this.readScheduler(id));
        Scheduler updatedScheduler = mapper.update(schedulerPayload, scheduler);

        return mapper.toDto(dao.save(updatedScheduler));
    }

    @Override
    @Transactional
    public void deleteScheduler(Long id) {
        dao.deleteById(id);
    }
}