package com.vnazarenko.updater.joblist;

import com.vnazarenko.updater.joblist.model.JobListDto;

import java.util.List;

public interface JobListService {

    JobListDto createJobList(JobListDto jobListDto);

    JobListDto readJobList(Long id);

    List<JobListDto> readJobLists();

    JobListDto updateJobList(Long id, JobListDto jobListDto);

    void deleteJobList(Long id);
}