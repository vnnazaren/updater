package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.DatabasePayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер Database
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dbs")
public class DatabaseController {
    private final DatabaseService databaseService;

    /**
     * Создание настроек БД
     *
     * @param databaseDto Тело запроса с DTO базы данных
     * @return объект DTO с новой созданной базой данных
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DatabasePayload createDatabase(@Validated(Marker.OnCreate.class) @RequestBody DatabasePayload databaseDto) {
        log.info("POST /dbs - %s".formatted(databaseDto));
        return databaseService.createDatabase(databaseDto);
    }

    /**
     * Получение настроек всех БД
     *
     * @return список с объектами DTO баз данных
     */
    @GetMapping
    public List<DatabasePayload> readDatabases() {
        log.info("GET /dbs");
        return databaseService.readDatabases();
    }

    /**
     * Получение настроек БД по номеру
     *
     * @param id идентификатор базы данных
     * @return объект DTO базы данных
     */
    @GetMapping("/{id}")
    public DatabasePayload readDatabase(@PathVariable("id") Long id) {
        log.info("GET /dbs/%d".formatted(id));
        return databaseService.readDatabase(id);
    }

    /**
     * Изменение настроек БД
     *
     * @param id          Идентификатор обновляемой базы данных
     * @param databaseDto Тело запроса с DTO базы данных
     * @return объект DTO базы данных с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public DatabasePayload updateDatabase(@PathVariable Long id,
                                          @Validated(Marker.OnUpdate.class) @RequestBody DatabasePayload databaseDto) {
        log.info("PATCH /dbs/%d - %s".formatted(id, databaseDto));
        return databaseService.updateDatabase(id, databaseDto);
    }

    /**
     * Удаление настроек БД
     *
     * @param id идентификатор базы данных
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDatabase(@PathVariable("id") Long id) {
        log.info("DELETE /dbs/%d".formatted(id));
        databaseService.deleteDatabase(id);
    }
}