package com.vnazarenko.updater.joblist.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobListMapper {

    JobList toEntity(JobListDto jobListDto);

    JobListDto toDto(JobList jobList);

    List<JobListDto> toDtoList(List<JobList> jobList);

    JobList update(JobListDto jobListDto, @MappingTarget JobList jobList);
}