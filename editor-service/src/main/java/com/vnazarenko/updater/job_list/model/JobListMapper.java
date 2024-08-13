package com.vnazarenko.updater.job_list.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface JobListMapper {

    @Mappings({@Mapping(target = "database.id", source = "databaseId"),
            @Mapping(target = "launch.id", source = "launchId"),
            @Mapping(target = "taskList.id", source = "taskListId")})
    JobList toEntity(JobListPayload jobListDto);

    @Mappings({@Mapping(target = "databaseId", source = "database.id"),
            @Mapping(target = "launchId", source = "launch.id"),
            @Mapping(target = "taskListId", source = "taskList.id")})
    JobListPayload toDto(JobList jobList);

    List<JobListPayload> toDtoList(List<JobList> jobList);

    @Mappings({@Mapping(target = "database.id", source = "databaseId"),
            @Mapping(target = "launch.id", source = "launchId"),
            @Mapping(target = "taskList.id", source = "taskListId")})
    JobList update(JobListPayload jobListDto, @MappingTarget JobList jobList);
}