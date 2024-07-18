package com.vnazarenko.updater.scheduler;

import com.vnazarenko.updater.scheduler.model.SchedulerDto;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер Scheduler
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedulers")
public class SchedulerController {
    private final SchedulerService schedulerService;

    /**
     * Создание будильника
     *
     * @param schedulerDto Тело запроса с DTO будильника
     * @return объект DTO с новым созданным будильником
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchedulerDto createSchedule(@Validated(Marker.OnCreate.class) @RequestBody SchedulerDto schedulerDto) {
        log.info("POST /schedulers - %s".formatted(schedulerDto));
        return schedulerService.createScheduler(schedulerDto);
    }

    /**
     * Получение всех будильников
     *
     * @return список с объектами DTO будильников
     */
    @GetMapping
    public List<SchedulerDto> readSchedules() {
        log.info("GET /schedulers");
        return schedulerService.readSchedulers();
    }

    /**
     * Получение будильника
     *
     * @param id идентификатор будильника
     * @return объект DTO будильника
     */
    @GetMapping("/{id}")
    public SchedulerDto readSchedule(@PathVariable("id") Long id) {
        log.info("GET /schedulers/%d".formatted(id));
        return schedulerService.readScheduler(id);
    }

    /**
     * Изменение будильника
     *
     * @param id           Идентификатор обновляемого будильника
     * @param schedulerDto Тело запроса с DTO будильника
     * @return объект DTO будильника с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public SchedulerDto updateSchedule(@PathVariable Long id,
                                       @Validated(Marker.OnUpdate.class) @RequestBody SchedulerDto schedulerDto) {
        log.info("PATCH /schedulers/%d - %s".formatted(id, schedulerDto));
        return schedulerService.updateScheduler(id, schedulerDto);
    }

    /**
     * Удаление будильника
     *
     * @param id идентификатор будильника
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduler(@PathVariable("id") Long id) {
        log.info("DELETE /schedulers/%d".formatted(id));
        schedulerService.deleteScheduler(id);
    }
}