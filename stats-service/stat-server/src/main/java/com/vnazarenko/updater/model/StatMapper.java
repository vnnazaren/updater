package com.vnazarenko.updater.model;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface StatMapper {

    Hit toHit(final HitDto hitDto);

    StatDto toUriStatDto(final Stat stat);
}