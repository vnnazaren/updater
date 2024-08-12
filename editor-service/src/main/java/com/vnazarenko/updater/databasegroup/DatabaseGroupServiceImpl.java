package com.vnazarenko.updater.databasegroup;

import com.vnazarenko.updater.databasegroup.model.DatabaseGroup;
import com.vnazarenko.updater.databasegroup.model.DatabaseGroupMapper;
import com.vnazarenko.updater.databasegroup.model.DatabaseGroupPayload;
import com.vnazarenko.updater.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DatabaseGroupServiceImpl implements DatabaseGroupService {
    private final DatabaseGroupStorage dao;
    private final DatabaseGroupMapper mapper;

    @Override
    @Transactional
    public DatabaseGroupPayload createDatabaseGroup(DatabaseGroupPayload databaseGroupPayload) {
        return mapper.toDto(dao.save(mapper.toEntity(databaseGroupPayload)));
    }

    @Override
    public DatabaseGroupPayload readDatabaseGroup(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("DatabaseGroup with id \"%d\" not found.".formatted(id))));
    }

    @Override
    public List<DatabaseGroupPayload> readDatabaseGroups() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public DatabaseGroupPayload updateDatabaseGroup(Long id, DatabaseGroupPayload databaseGroupPayload) {
        DatabaseGroup databaseGroup = mapper.toEntity(this.readDatabaseGroup(id));
        DatabaseGroup updatedDatabaseGroup = mapper.update(databaseGroupPayload, databaseGroup);

        return mapper.toDto(dao.save(updatedDatabaseGroup));
    }

    @Override
    @Transactional
    public void deleteDatabaseGroup(Long id) {
        dao.deleteById(id);
    }
}