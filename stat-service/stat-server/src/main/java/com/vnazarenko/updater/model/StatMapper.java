package com.vnazarenko.updater.model;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface StatMapper {

    @Mappings({@Mapping(target = "hitDate", source = "timestamp"),
            @Mapping(target = "id", ignore = true)})
    Hit toHit(final HitDto hitDto);

    StatDto toUriStatDto(final Stat stat);
}