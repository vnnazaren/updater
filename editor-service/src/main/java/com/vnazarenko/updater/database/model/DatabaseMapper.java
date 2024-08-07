package com.vnazarenko.updater.database.model;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DatabaseMapper {

    Database toEntity(DatabasePayload databasePayload);

    DatabasePayload toDto(Database database);

    List<DatabasePayload> toDtoList(List<Database> databaseList);

    Database update(DatabasePayload databasePayload, @MappingTarget Database database);
}