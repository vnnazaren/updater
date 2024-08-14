package com.vnazarenko.updater.scenario.controller;

import com.vnazarenko.updater.scenario.ScenarioService;
import com.vnazarenko.updater.scenario.model.ScenarioPayload;
import com.vnazarenko.updater.stat.StatClient;
import com.vnazarenko.updater.stat.model.StatIn;
import com.vnazarenko.updater.util.Marker;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * Класс-контроллер Scenario
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("scenarios")
public class ScenariosNewController {

    private final ScenarioService scenarioService;
    private final StatClient statClient;

    /**
     * Получение страницы со списком всех БД
     */
    @GetMapping("list")
    public String getScenariosList(Model model) {
        model.addAttribute("scenarios", this.scenarioService.readScenarios());
        return "scenarios/list";
    }

    /**
     * Получение страницы для создания новой БД
     */
    @GetMapping("create")
    public String getNewScenarioPage() {
        return "scenarios/create";
    }

    /**
     * Создание настроек БД
     */
    @PostMapping("create")
    public String createScenario(@Validated(Marker.OnCreate.class) ScenarioPayload payload,
                                 BindingResult bindingResult,
                                 HttpServletRequest httpServletRequest,
                                 Model model) {

        log.info("POST /scenarios/create - %s".formatted(payload));
        statClient.saveHit(new StatIn("editor-service",
                httpServletRequest.getRemoteAddr(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()));

        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "scenarios/create";
        } else {
            ScenarioPayload scenario = this.scenarioService.createScenario(payload);
            return "redirect:/scenarios/%d".formatted(scenario.id());
        }
    }
}