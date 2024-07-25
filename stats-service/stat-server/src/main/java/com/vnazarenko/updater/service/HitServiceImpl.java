package com.vnazarenko.updater.service;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import com.vnazarenko.updater.exceptions.BadRequestException;
import com.vnazarenko.updater.model.StatMapper;
import com.vnazarenko.updater.storage.HitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс-сервис HIT
 */
@RequiredArgsConstructor
@Service
public class HitServiceImpl implements HitService {
    private final HitRepository hitRepository;
    private final StatMapper mapper;

    @Override
    @Transactional
    public void saveHit(HitDto hitDtoRequest) {
        hitRepository.save(mapper.toHit(hitDtoRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatDto> getHitsInfo(List<String> uris,
                                     Boolean unique,
                                     LocalDateTime start,
                                     LocalDateTime end) {

        checkCorrectDates(start, end);

        if (uris == null || uris.isEmpty()) {
            if (unique) {
                return hitRepository.getUniqueHits(start, end).stream()
                        .map(mapper::toUriStatDto)
                        .collect(Collectors.toList());
            } else {
                return hitRepository.getHits(start, end).stream()
                        .map(mapper::toUriStatDto)
                        .collect(Collectors.toList());
            }
        } else {
            if (unique) {
                return hitRepository.getUniqueHitsByUris(uris, start, end).stream()
                        .map(mapper::toUriStatDto)
                        .collect(Collectors.toList());
            } else {
                return hitRepository.getHitsByUris(uris, start, end).stream()
                        .map(mapper::toUriStatDto)
                        .collect(Collectors.toList());
            }
        }
    }

    private void checkCorrectDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new BadRequestException("Дата начала должна быть раньше даты окончания.");
        }
    }
}