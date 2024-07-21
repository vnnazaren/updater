package com.vnazarenko.updater.database.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DatabaseMapper {

    Database toEntity(DatabasePayload databaseDto);

    DatabasePayload toDto(Database database);

    List<DatabasePayload> toDtoList(List<Database> databaseList);

    Database update(DatabasePayload databaseDto, @MappingTarget Database database);
}