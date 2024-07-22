package com.vnazarenko.updater.scenario;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.scenario.model.Scenario;
import com.vnazarenko.updater.scenario.model.ScenarioMapper;
import com.vnazarenko.updater.scenario.model.ScenarioPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ScenarioServiceImpl implements ScenarioService {
    private final ScenarioStorage dao;
    private final ScenarioMapper mapper;

    @Override
    @Transactional
    public ScenarioPayload createScenario(ScenarioPayload scenarioPayload) {
        return mapper.toDto(dao.save(mapper.toEntity(scenarioPayload)));
    }

    @Override
    public ScenarioPayload readScenario(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("TaskList with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<ScenarioPayload> readScenarios() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public ScenarioPayload updateScenario(Long id, ScenarioPayload scenarioPayload) {
        Scenario scenario = mapper.toEntity(this.readScenario(id));
        Scenario updatedScenario = mapper.update(scenarioPayload, scenario);

        return mapper.toDto(dao.save(dao.save(updatedScenario)));
    }

    @Override
    @Transactional
    public void deleteScenario(Long id) {
        dao.deleteById(id);
    }
}