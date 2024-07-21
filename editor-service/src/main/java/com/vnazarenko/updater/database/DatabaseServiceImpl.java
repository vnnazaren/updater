package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.Database;
import com.vnazarenko.updater.database.model.DatabasePayload;
import com.vnazarenko.updater.database.model.DatabaseMapper;
import com.vnazarenko.updater.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DatabaseServiceImpl implements DatabaseService {
    private final DatabaseStorage dao;
    private final DatabaseMapper mapper;

    @Override
    @Transactional
    public DatabasePayload createDatabase(DatabasePayload databaseDto) {
        return mapper.toDto(dao.save(mapper.toEntity(databaseDto)));
    }

    @Override
    public DatabasePayload readDatabase(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Database with id \"%d\" not found.".formatted(id))));
    }

    @Override
    public List<DatabasePayload> readDatabases() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public DatabasePayload updateDatabase(Long id, DatabasePayload databaseDto) {
        Database database = mapper.toEntity(this.readDatabase(id));
        Database updatedDatabase = mapper.update(databaseDto, database);

        return mapper.toDto(dao.save(updatedDatabase));
    }

    @Override
    @Transactional
    public void deleteDatabase(Long id) {
        dao.deleteById(id);
    }
}