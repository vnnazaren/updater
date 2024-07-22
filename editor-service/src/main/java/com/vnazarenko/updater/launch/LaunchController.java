package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.launch.model.LaunchPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер Launch
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/launches")
public class LaunchController {
    private final LaunchService launchService;

    /**
     * Создание запуска
     *
     * @param launchPayload Тело запроса с DTO запуска
     * @return объект DTO с новым созданным запуском
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LaunchPayload createLaunchInstance(@Validated(Marker.OnCreate.class) @RequestBody LaunchPayload launchPayload) {
        log.info("POST /launches - %s".formatted(launchPayload));
        return launchService.createLaunch(launchPayload);
    }

    /**
     * Получение всех запусков
     *
     * @return список с объектами DTO запусков
     */
    @GetMapping
    public List<LaunchPayload> readLaunches() {
        log.info("GET /launches");
        return launchService.readLaunches();
    }

    /**
     * Получение запуска по ID
     *
     * @param id идентификатор запуска
     * @return объект DTO запуска
     */
    @GetMapping("/{id}")
    public LaunchPayload readLaunch(@PathVariable("id") Long id) {
        log.info("GET /launches/%d".formatted(id));
        return launchService.readLaunch(id);
    }

    /**
     * Изменение запуска
     *
     * @param id            идентификатор запуска
     * @param launchPayload Тело запроса с DTO запуска
     * @return объект DTO запуска с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public LaunchPayload updateLaunch(@PathVariable Long id,
                                      @Validated(Marker.OnUpdate.class) @RequestBody LaunchPayload launchPayload) {
        log.info("PATCH /launches/%d - %s".formatted(id, launchPayload));
        return launchService.updateLaunch(id, launchPayload);
    }

    /**
     * Удаление запуска
     *
     * @param id идентификатор запуска
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLaunch(@PathVariable("id") Long id) {
        log.info("DELETE /launches/%d".formatted(id));
        launchService.deleteLaunch(id);
    }
}