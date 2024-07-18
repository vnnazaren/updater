package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.Database;
import com.vnazarenko.updater.database.model.DatabaseDto;
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
    public DatabaseDto createDatabase(DatabaseDto databaseDto) {
        return mapper.toDto(dao.save(mapper.toEntity(databaseDto)));
    }

    @Override
    public DatabaseDto readDatabase(Long id) {
        return mapper.toDto(dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Database with id \"%d\" not found.".formatted(id))));
    }

    @Override
    public List<DatabaseDto> readDatabases() {
        return mapper.toDtoList(dao.findAll());
    }

    @Override
    @Transactional
    public DatabaseDto updateDatabase(Long id, DatabaseDto databaseDto) {
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