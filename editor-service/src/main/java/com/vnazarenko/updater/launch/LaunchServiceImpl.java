package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.launch.model.Launch;
import com.vnazarenko.updater.launch.model.LaunchMapper;
import com.vnazarenko.updater.launch.model.LaunchPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class LaunchServiceImpl implements LaunchService {
    private final LaunchStorage dao;
    private final LaunchMapper mapper;

    @Override
    @Transactional
    public LaunchPayload createLaunch(LaunchPayload launchPayload) {
        return mapper.toDto(dao.save(mapper.toEntity(launchPayload)));
    }

    @Override
    public LaunchPayload readLaunch(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Launch with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<LaunchPayload> readLaunches() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public LaunchPayload updateLaunch(Long id, LaunchPayload launchPayload) {
        Launch launch = mapper.toEntity(this.readLaunch(id));
        Launch updatedLaunch = mapper.update(launchPayload, launch);

        return mapper.toDto(dao.save(updatedLaunch));
    }

    @Override
    @Transactional
    public void deleteLaunch(Long id) {
        dao.deleteById(id);
    }
}