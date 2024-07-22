package com.vnazarenko.updater.scenario;

import com.vnazarenko.updater.scenario.model.ScenarioPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер Scenario
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/scenarios")
public class ScenarioController {
    private final ScenarioService scenarioService;

    /**
     * Создание сценария
     *
     * @param scenarioPayload - Тело запроса с DTO сценария
     * @return объект DTO с новым созданным сценарием
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScenarioPayload createScenario(@Validated(Marker.OnCreate.class) @RequestBody ScenarioPayload scenarioPayload) {
        log.info("POST /scenarios - %s".formatted(scenarioPayload));
        return scenarioService.createScenario(scenarioPayload);
    }

    /**
     * Получение всех сценариев
     *
     * @return список с объектами DTO всех сценариев
     */
    @GetMapping
    public List<ScenarioPayload> readScenarios() {
        log.info("GET /scenarios");
        return scenarioService.readScenarios();
    }

    /**
     * Получение сценария
     *
     * @param id - идентификатор сценария
     * @return объект DTO сценария
     */
    @GetMapping("/{id}")
    public ScenarioPayload readScenario(@PathVariable("id") Long id) {
        log.info("GET /scenarios/%d".formatted(id));
        return scenarioService.readScenario(id);
    }

    /**
     * Изменение сценария
     *
     * @param id              - идентификатор сценария
     * @param scenarioPayload Тело запроса с DTO сценария
     * @return - объект DTO сценария с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public ScenarioPayload updateScenario(@PathVariable("id") Long id,
                                          @Validated(Marker.OnUpdate.class) @RequestBody ScenarioPayload scenarioPayload) {
        log.info("PATCH /scenarios/%d - %s".formatted(id, scenarioPayload));
        return scenarioService.updateScenario(id, scenarioPayload);
    }

    /**
     * Удаление сценария
     *
     * @param id - идентификатор сценария
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScenario(@PathVariable("id") Long id) {
        log.info("DELETE /scenarios/%d".formatted(id));
        scenarioService.deleteScenario(id);
    }
}