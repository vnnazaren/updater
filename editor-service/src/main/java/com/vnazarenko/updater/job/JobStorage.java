package com.vnazarenko.updater.job;

import com.vnazarenko.updater.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface JobStorage extends JpaRepository<Job, Long> {

    Set<Job> findAllByIdIn(Set<Long> jobs);
}