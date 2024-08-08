package com.vnazarenko.updater.databasegroup;

import com.vnazarenko.updater.databasegroup.model.DatabaseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseGroupStorage extends JpaRepository<DatabaseGroup, Long> {

}