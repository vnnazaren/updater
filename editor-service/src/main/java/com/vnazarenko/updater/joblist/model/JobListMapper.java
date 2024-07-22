package com.vnazarenko.updater.joblist.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobListMapper {

    JobList toEntity(JobListPayload jobListDto);

    JobListPayload toDto(JobList jobList);

    List<JobListPayload> toDtoList(List<JobList> jobList);

    JobList update(JobListPayload jobListDto, @MappingTarget JobList jobList);
}