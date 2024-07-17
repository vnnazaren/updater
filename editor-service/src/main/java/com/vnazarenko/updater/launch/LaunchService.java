package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.launch.model.LaunchDto;

import java.util.List;

public interface LaunchService {

    LaunchDto createLaunch(LaunchDto launchDto);

    LaunchDto readLaunch(Long id);

    List<LaunchDto> readLaunches();

    LaunchDto updateLaunch(Long id, LaunchDto launchDto);

    void deleteLaunch(Long id);
}