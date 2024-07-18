package com.vnazarenko.updater.scheduler;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.scheduler.model.Scheduler;
import com.vnazarenko.updater.scheduler.model.SchedulerDto;
import com.vnazarenko.updater.scheduler.model.SchedulerMapper;
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
    public SchedulerDto createScheduler(SchedulerDto schedulerDto) {
        return mapper.toDto(dao.save(mapper.toEntity(schedulerDto)));
    }

    @Override
    public SchedulerDto readScheduler(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Scheduler with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<SchedulerDto> readSchedulers() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public SchedulerDto updateScheduler(Long id, SchedulerDto schedulerDto) {
        Scheduler scheduler = mapper.toEntity(this.readScheduler(id));
        Scheduler updatedScheduler = mapper.update(schedulerDto, scheduler);

        return mapper.toDto(dao.save(updatedScheduler));
    }

    @Override
    @Transactional
    public void deleteScheduler(Long id) {
        dao.deleteById(id);
    }
}