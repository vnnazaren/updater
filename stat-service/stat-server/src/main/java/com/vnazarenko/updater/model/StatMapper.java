package com.vnazarenko.updater.model;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface StatMapper {

    @Mappings({@Mapping(target = "hitDate", source = "timestamp"),
            @Mapping(target = "id", ignore = true)})
    Hit toHit(final HitDto hitDto);

    StatDto toUriStatDto(final Stat stat);
}