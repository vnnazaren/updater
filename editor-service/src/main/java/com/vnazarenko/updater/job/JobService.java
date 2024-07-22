package com.vnazarenko.updater.job;

import com.vnazarenko.updater.job.model.Job;
import com.vnazarenko.updater.job.model.JobPayload;

import java.util.List;
import java.util.Set;

public interface JobService {

    JobPayload createJob(JobPayload taskInstanceDto);

    JobPayload readJob(Long id);

    List<JobPayload> readJobs();

    Set<Job> readJobsByIdIn(Set<Long> jobs);

    JobPayload updateJob(Long id, JobPayload jobPayload);

    void deleteJob(Long id);
}