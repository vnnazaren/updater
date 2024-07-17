package com.vnazarenko.updater.launch;

import com.vnazarenko.updater.launch.model.Launch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchStorage extends JpaRepository<Launch, Long> {

}