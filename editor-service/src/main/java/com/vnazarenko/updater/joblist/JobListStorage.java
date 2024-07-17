package com.vnazarenko.updater.joblist;

import com.vnazarenko.updater.joblist.model.JobList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobListStorage extends JpaRepository<JobList, Long> {

}