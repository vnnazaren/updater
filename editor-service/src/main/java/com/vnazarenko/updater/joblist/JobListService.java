package com.vnazarenko.updater.joblist;

import com.vnazarenko.updater.joblist.model.JobListPayload;

import java.util.List;

public interface JobListService {

    JobListPayload createJobList(JobListPayload jobListPayload);

    JobListPayload readJobList(Long id);

    List<JobListPayload> readJobLists();

    JobListPayload updateJobList(Long id, JobListPayload jobListPayload);

    void deleteJobList(Long id);
}