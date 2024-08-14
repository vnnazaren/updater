package com.vnazarenko.updater.scenario.controller;

import com.vnazarenko.updater.scenario.ScenarioService;
import com.vnazarenko.updater.scenario.model.ScenarioPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер Scenario
 */
@Controller
@RequestMapping(("scenarios/{scenarioId:\\d+}"))
@RequiredArgsConstructor
public class ScenarioNewController {

    private final ScenarioService scenarioService;

    /**
     * Описание модели
     *
     * @param scenarioId идентификатор базы данных
     * @return Получение из локальной БД объекта с настройками базы данных
     */
    @ModelAttribute("scenario")
    public ScenarioPayload scenario(@PathVariable("scenarioId") Long scenarioId) {
        return this.scenarioService.readScenario(scenarioId);
    }

    /**
     * Получение настроек БД по номеру
     */
    @GetMapping
    public String getScenario() {
        return "scenarios/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getScenarioEditPage() {
        return "scenarios/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateScenario(@ModelAttribute(name = "scenario", binding = false) ScenarioPayload scenario,
                                 @Validated(Marker.OnUpdate.class) ScenarioPayload payload,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "scenarios/edit";
        } else {
            this.scenarioService.updateScenario(payload.id(), payload);
            return "redirect:/scenarios/%d".formatted(scenario.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    public String deleteScenario(@ModelAttribute("scenario") ScenarioPayload scenario) {
        this.scenarioService.deleteScenario(scenario.id());
        return "redirect:/scenarios/list";
    }
}