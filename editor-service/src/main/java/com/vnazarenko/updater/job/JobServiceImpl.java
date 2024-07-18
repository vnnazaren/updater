package com.vnazarenko.updater.job;

import com.vnazarenko.updater.exception.BadRequestException;
import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.job.model.Job;
import com.vnazarenko.updater.job.model.JobDto;
import com.vnazarenko.updater.job.model.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class JobServiceImpl implements JobService {
    private final JobStorage dao;
    private final JobMapper mapper;

    @Override
    @Transactional
    public JobDto createJob(JobDto jobDto) {
        Job job = mapper.toEntity(jobDto);

        Set<Long> ancestors = jobDto.getAncestors();
        if (ancestors != null) {
            job.setAncestors(this.readJobsByIdIn(ancestors));
        }

        try {
            return mapper.toDto(dao.save(job));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(
                    "Ошибка при создании задачи: %s".formatted(jobDto));
        }
    }

    @Override
    public JobDto readJob(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<JobDto> readJobs() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    public Set<Job> readJobsByIdIn(Set<Long> jobs) {
        return dao.findAllByIdIn(jobs);
    }

    @Override
    @Transactional
    public JobDto updateJob(Long id, JobDto jobDto) {
        Job job = mapper.toEntity(this.readJob(id));
        Job updatedJob = mapper.update(jobDto, job);

        return mapper.toDto(dao.save(updatedJob));
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        dao.deleteById(id);
    }
}