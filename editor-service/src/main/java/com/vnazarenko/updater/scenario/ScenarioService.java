package com.vnazarenko.updater.scenario;

import com.vnazarenko.updater.scenario.model.ScenarioDto;

import java.util.List;

public interface ScenarioService {

    ScenarioDto createScenario(ScenarioDto scenarioDto);

    ScenarioDto readScenario(Long id);

    List<ScenarioDto> readScenarios();

    ScenarioDto updateScenario(Long id, ScenarioDto scenarioDto);

    void deleteScenario(Long id);
}