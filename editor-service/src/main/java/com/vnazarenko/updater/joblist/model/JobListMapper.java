package com.vnazarenko.updater.joblist.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobListMapper {

    @Mapping(target = "id", ignore = true)
    JobList toEntity(JobListDto jobListDto);

    JobListDto toDto(JobList jobList);

    List<JobListDto> toDtoList(List<JobList> jobList);

    JobList update(JobListDto jobListDto, @MappingTarget JobList jobList);
}