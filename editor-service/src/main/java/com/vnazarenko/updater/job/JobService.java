package com.vnazarenko.updater.job;

import com.vnazarenko.updater.job.model.Job;
import com.vnazarenko.updater.job.model.JobDto;

import java.util.List;
import java.util.Set;

public interface JobService {

    JobDto createJob(JobDto taskInstanceDto);

    JobDto readJob(Long id);

    List<JobDto> readJobs();

    Set<Job> readJobsByIdIn(Set<Long> jobs);

    JobDto updateJob(Long id, JobDto jobDto);

    void deleteJob(Long id);
}