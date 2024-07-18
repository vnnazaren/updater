package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.exception.EntityNotFoundException;
import com.vnazarenko.updater.launch.model.Launch;
import com.vnazarenko.updater.launch.model.LaunchDto;
import com.vnazarenko.updater.launch.model.LaunchMapper;
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
    public LaunchDto createLaunch(LaunchDto launchDto) {
        return mapper.toDto(dao.save(mapper.toEntity(launchDto)));
    }

    @Override
    public LaunchDto readLaunch(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Launch with id \"%d\" not found".formatted(id))));
    }

    @Override
    public List<LaunchDto> readLaunches() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public LaunchDto updateLaunch(Long id, LaunchDto launchDto) {
        Launch launch = mapper.toEntity(this.readLaunch(id));
        Launch updatedLaunch = mapper.update(launchDto, launch);

        return mapper.toDto(dao.save(updatedLaunch));
    }

    @Override
    @Transactional
    public void deleteLaunch(Long id) {
        dao.deleteById(id);
    }
}