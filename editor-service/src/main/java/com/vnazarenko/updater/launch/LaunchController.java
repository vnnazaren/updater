package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.launch.model.LaunchDto;
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
     * @param launchDto Тело запроса с DTO запуска
     * @return объект DTO с новым созданным запуском
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LaunchDto createLaunchInstance(@Validated(Marker.OnCreate.class) @RequestBody LaunchDto launchDto) {
        log.info("POST /launches - %s".formatted(launchDto));
        return launchService.createLaunch(launchDto);
    }

    /**
     * Получение всех запусков
     *
     * @return список с объектами DTO запусков
     */
    @GetMapping
    public List<LaunchDto> readLaunches() {
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
    public LaunchDto readLaunch(@PathVariable("id") Long id) {
        log.info("GET /launches/%d".formatted(id));
        return launchService.readLaunch(id);
    }

    /**
     * Изменение запуска
     *
     * @param id        идентификатор запуска
     * @param launchDto Тело запроса с DTO запуска
     * @return объект DTO запуска с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public LaunchDto updateLaunch(@PathVariable Long id,
                                  @Validated(Marker.OnUpdate.class) @RequestBody LaunchDto launchDto) {
        log.info("PATCH /launches/%d - %s".formatted(id, launchDto));
        return launchService.updateLaunch(id, launchDto);
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