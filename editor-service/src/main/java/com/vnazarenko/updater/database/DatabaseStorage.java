package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.Database;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseStorage extends JpaRepository<Database, Long> {

}