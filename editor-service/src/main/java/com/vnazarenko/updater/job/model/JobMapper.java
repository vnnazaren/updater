package com.vnazarenko.updater.job.model;

import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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
                .map(jobId ->
                        Job.builder()
                                .id(jobId)
                                .build()
                )
                .collect(Collectors.toSet());
    }

    @Mappings({
            @Mapping(target = "jobList.id", source = "jobListId"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "idSetToJobSet")
    })
    Job toEntity(JobDto JobDto);

    @Mappings({
            @Mapping(target = "jobListId", source = "jobList.id"),
            @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "jobSetToIdSet")
    })
    JobDto toDto(Job Job);

    List<JobDto> toDtoList(List<Job> jobList);

    @Mapping(target = "ancestors", source = "ancestors", qualifiedByName = "idSetToJobSet")
    Job update(JobDto jobDto, @MappingTarget Job job);
}