package com.vnazarenko.updater.job;

import com.vnazarenko.updater.exception.BadRequestException;
import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.job.model.Job;
import com.vnazarenko.updater.job.model.JobMapper;
import com.vnazarenko.updater.job.model.JobPayload;
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
    public JobPayload createJob(JobPayload jobPayload) {
        Job job = mapper.toEntity(jobPayload);

        Set<Long> ancestors = jobPayload.ancestors();
        if (ancestors != null) {
            job.setAncestors(this.readJobsByIdIn(ancestors));
        }

        try {
            return mapper.toDto(dao.save(job));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException(
                    "Ошибка при создании задачи: %s".formatted(jobPayload));
        }
    }

    @Override
    public JobPayload readJob(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Job with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<JobPayload> readJobs() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    public Set<Job> readJobsByIdIn(Set<Long> jobs) {
        return dao.findAllByIdIn(jobs);
    }

    @Override
    @Transactional
    public JobPayload updateJob(Long id, JobPayload jobPayload) {
        Job job = mapper.toEntity(this.readJob(id));
        Job updatedJob = mapper.update(jobPayload, job);

        return mapper.toDto(dao.save(updatedJob));
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        dao.deleteById(id);
    }
}