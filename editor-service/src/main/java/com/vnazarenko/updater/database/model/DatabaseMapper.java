package com.vnazarenko.updater.database.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DatabaseMapper {

    @Mapping(target = "id", ignore = true)
    Database toEntity(DatabaseDto databaseDto);

    DatabaseDto toDto(Database database);

    List<DatabaseDto> toDtoList(List<Database> databaseList);

    Database update(DatabaseDto databaseDto, @MappingTarget Database database);
}