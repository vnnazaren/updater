package com.vnazarenko.updater.joblist;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.joblist.model.JobList;
import com.vnazarenko.updater.joblist.model.JobListDto;
import com.vnazarenko.updater.joblist.model.JobListMapper;
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
    public JobListDto createJobList(JobListDto jobListDto) {
        return mapper.toDto(dao.save(mapper.toEntity(jobListDto)));
    }

    @Override
    public JobListDto readJobList(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("JobList with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<JobListDto> readJobLists() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public JobListDto updateJobList(Long id, JobListDto jobListDto) {
        JobList jobList = mapper.toEntity(this.readJobList(id));
        JobList updatedJobList = mapper.update(jobListDto, jobList);

        return mapper.toDto(dao.save(updatedJobList));
    }

    @Override
    @Transactional
    public void deleteJobList(Long id) {
        dao.deleteById(id);
    }
}