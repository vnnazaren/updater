package com.vnazarenko.updater.model;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StatMapper {

    @Mapping(target = "hitDate", source = "timestamp")
    Hit toHit(final HitDto hitDto);

    StatDto toUriStatDto(final Stat stat);
}