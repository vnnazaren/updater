package com.vnazarenko.updater.job_list;

import com.vnazarenko.updater.job_list.model.JobListPayload;

import java.util.List;

public interface JobListService {

    JobListPayload createJobList(JobListPayload jobListPayload);

    JobListPayload readJobList(Long id);

    List<JobListPayload> readJobLists();

    JobListPayload updateJobList(Long id, JobListPayload jobListPayload);

    void deleteJobList(Long id);
}