package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.DatabaseDto;

import java.util.List;

public interface DatabaseService {

    DatabaseDto createDatabase(DatabaseDto databaseDto);

    DatabaseDto readDatabase(Long id);

    List<DatabaseDto> readDatabases();

    DatabaseDto updateDatabase(Long id, DatabaseDto databaseDto);

    void deleteDatabase(Long id);
}