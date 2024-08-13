package com.vnazarenko.updater.database_group;

import com.vnazarenko.updater.database_group.model.DatabaseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseGroupStorage extends JpaRepository<DatabaseGroup, Long> {

}