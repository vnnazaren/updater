package com.vnazarenko.updater.scenario;

import com.vnazarenko.updater.scenario.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioStorage extends JpaRepository<Scenario, Long> {

}