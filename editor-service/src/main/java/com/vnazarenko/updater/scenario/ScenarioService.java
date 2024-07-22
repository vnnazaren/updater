package com.vnazarenko.updater.scenario;

import com.vnazarenko.updater.scenario.model.ScenarioPayload;

import java.util.List;

public interface ScenarioService {

    ScenarioPayload createScenario(ScenarioPayload scenarioPayload);

    ScenarioPayload readScenario(Long id);

    List<ScenarioPayload> readScenarios();

    ScenarioPayload updateScenario(Long id, ScenarioPayload scenarioPayload);

    void deleteScenario(Long id);
}