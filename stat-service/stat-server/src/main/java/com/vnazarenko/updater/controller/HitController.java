package com.vnazarenko.updater.controller;

import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;
import com.vnazarenko.updater.service.HitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.vnazarenko.updater.util.Const.DATE_TIME_FORMAT;

/**
 * Класс-контроллер HIT
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class HitController {
    private final HitService hitService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHit(@RequestBody HitDto hitDtoRequest) {
        log.info("HitController: POST /hit - hitDtoRequest: {}", hitDtoRequest);
        hitService.saveHit(hitDtoRequest);
    }

    @GetMapping("/stats")
    public List<StatDto> getHitsInfo(
            @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = DATE_TIME_FORMAT) LocalDateTime end,
            @RequestParam(defaultValue = "false") boolean unique,
            @RequestParam(required = false) List<String> uris) {
        log.info("HitController: GET /stats?start={}&end={}&uris={}&unique={}", uris, unique, start, end);
        if (uris == null) {
            uris = List.of();
        }
        return hitService.getHitsInfo(uris, unique, start, end);
    }
}