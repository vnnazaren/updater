package com.vnazarenko.updater.job.model;

import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface JobMapper {

    @Named("jobSetToIdSet")
    default Set<Long> mapToIdSet(Set<Job> ancestors) {
        if (ancestors == null) {
            return Collections.emptySet();
        }

        return ancestors.stream()
                .map(Job::getId)
                .collect(Collectors.toSet());
    }

    @Named("idSetToJobSet")   // todo - возможно это не нужно - просто сделать "ignore"
    default Set<Job> mapToJobSet(Set<Long> ancestors) {
        if (ancestors == null) {
            return Collections.emptySet();
        }
        return ancestors.stream()
                .map(jobId -> Job.builder().id(jobId).build())
                .collect(Collectors.toSet());
    }

    @Mappings({@Mapping(target = "jobList.id", source = "jobListId"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "idSetToJobSet")})
    Job toEntity(JobPayload jobPayload);

    @Mappings({@Mapping(target = "jobListId", source = "jobList.id"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "jobSetToIdSet")})
    JobPayload toDto(Job Job);

    List<JobPayload> toDtoList(List<Job> jobList);

    @Mappings({@Mapping(target = "jobList.id", source = "jobListId"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "idSetToJobSet")})
    Job update(JobPayload jobPayload, @MappingTarget Job job);
}