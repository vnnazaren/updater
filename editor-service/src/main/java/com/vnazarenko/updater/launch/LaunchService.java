package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.launch.model.LaunchPayload;

import java.util.List;

public interface LaunchService {

    LaunchPayload createLaunch(LaunchPayload launchPayload);

    LaunchPayload readLaunch(Long id);

    List<LaunchPayload> readLaunches();

    LaunchPayload updateLaunch(Long id, LaunchPayload launchPayload);

    void deleteLaunch(Long id);
}