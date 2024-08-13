package com.vnazarenko.updater.database_group;

import com.vnazarenko.updater.database_group.model.DatabaseGroupPayload;

import java.util.List;

public interface DatabaseGroupService {

    DatabaseGroupPayload createDatabaseGroup(DatabaseGroupPayload databaseGroupPayload);

    DatabaseGroupPayload readDatabaseGroup(Long id);

    List<DatabaseGroupPayload> readDatabaseGroups();

    DatabaseGroupPayload updateDatabaseGroup(Long id, DatabaseGroupPayload databaseGroupPayload);

    void deleteDatabaseGroup(Long id);
}