package com.vnazarenko.updater.joblist;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.joblist.model.JobList;
import com.vnazarenko.updater.joblist.model.JobListMapper;
import com.vnazarenko.updater.joblist.model.JobListPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class JobListServiceImpl implements JobListService {
    private final JobListStorage dao;
    private final JobListMapper mapper;

    @Override
    @Transactional
    public JobListPayload createJobList(JobListPayload jobListPayload) {
        return mapper.toDto(dao.save(mapper.toEntity(jobListPayload)));
    }

    @Override
    public JobListPayload readJobList(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("JobList with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<JobListPayload> readJobLists() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public JobListPayload updateJobList(Long id, JobListPayload jobListPayload) {
        JobList jobList = mapper.toEntity(this.readJobList(id));
        JobList updatedJobList = mapper.update(jobListPayload, jobList);

        return mapper.toDto(dao.save(updatedJobList));
    }

    @Override
    @Transactional
    public void deleteJobList(Long id) {
        dao.deleteById(id);
    }
}