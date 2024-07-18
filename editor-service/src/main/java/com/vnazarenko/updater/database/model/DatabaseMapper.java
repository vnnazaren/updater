package com.vnazarenko.updater.database.model;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DatabaseMapper {

    Database toEntity(DatabaseDto databaseDto);

    DatabaseDto toDto(Database database);

    List<DatabaseDto> toDtoList(List<Database> databaseList);

    Database update(DatabaseDto databaseDto, @MappingTarget Database database);
}