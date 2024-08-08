package com.vnazarenko.updater.databasegroup.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DatabaseGroupMapper {

    DatabaseGroup toEntity(DatabaseGroupPayload databaseGroupPayload);

    DatabaseGroupPayload toDto(DatabaseGroup databaseGroup);

    List<DatabaseGroupPayload> toDtoList(List<DatabaseGroup> databaseGroupList);

    DatabaseGroup update(DatabaseGroupPayload databaseGroupPayload, @MappingTarget DatabaseGroup databaseGroup);
}