package com.vnazarenko.updater.job_list;

import com.vnazarenko.updater.job_list.model.JobList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListStorage extends JpaRepository<JobList, Long> {

}