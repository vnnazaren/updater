package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.DatabasePayload;

import java.util.List;

public interface DatabaseService {

    DatabasePayload createDatabase(DatabasePayload databaseDto);

    DatabasePayload readDatabase(Long id);

    List<DatabasePayload> readDatabases();

    DatabasePayload updateDatabase(Long id, DatabasePayload databaseDto);

    void deleteDatabase(Long id);
}