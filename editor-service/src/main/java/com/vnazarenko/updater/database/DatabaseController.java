package com.vnazarenko.updater.database;

import com.vnazarenko.updater.database.model.DatabasePayload;
import com.vnazarenko.updater.util.Marker;
import com.vnazarenko.updater.util.StatSaver;
import jakarta.servlet.http.HttpServletRequest;
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
    private final StatSaver statSaver;

    /**
     * Создание настроек БД
     *
     * @param databasePayload Тело запроса с DTO базы данных
     * @return объект DTO с новой созданной базой данных
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DatabasePayload createDatabase(@Validated(Marker.OnCreate.class) @RequestBody DatabasePayload databasePayload,
                                          HttpServletRequest httpServletRequest) {
        log.info("POST /dbs - %s".formatted(databasePayload));
        statSaver.saveHit(httpServletRequest.getRemoteAddr(), httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        return databaseService.createDatabase(databasePayload);
    }

    /**
     * Получение настроек всех БД
     *
     * @return список с объектами DTO баз данных
     */
    @GetMapping
    public List<DatabasePayload> readDatabases(HttpServletRequest httpServletRequest) {
        log.info("GET /dbs");
        statSaver.saveHit(httpServletRequest.getRemoteAddr(), httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        return databaseService.readDatabases();
    }

    /**
     * Получение настроек БД по номеру
     *
     * @param id идентификатор базы данных
     * @return объект DTO базы данных
     */
    @GetMapping("/{id}")
    public DatabasePayload readDatabase(@PathVariable("id") Long id,
                                        HttpServletRequest httpServletRequest) {
        log.info("GET /dbs/%d".formatted(id));
        statSaver.saveHit(httpServletRequest.getRemoteAddr(), httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        return databaseService.readDatabase(id);
    }

    /**
     * Изменение настроек БД
     *
     * @param id              Идентификатор обновляемой базы данных
     * @param databasePayload Тело запроса с DTO базы данных
     * @return объект DTO базы данных с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public DatabasePayload updateDatabase(@PathVariable Long id,
                                          @Validated(Marker.OnUpdate.class) @RequestBody DatabasePayload databasePayload,
                                          HttpServletRequest httpServletRequest) {
        log.info("PATCH /dbs/%d - %s".formatted(id, databasePayload));
        statSaver.saveHit(httpServletRequest.getRemoteAddr(), httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        return databaseService.updateDatabase(id, databasePayload);
    }

    /**
     * Удаление настроек БД
     *
     * @param id идентификатор базы данных
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDatabase(@PathVariable("id") Long id,
                               HttpServletRequest httpServletRequest) {
        log.info("DELETE /dbs/%d".formatted(id));
        statSaver.saveHit(httpServletRequest.getRemoteAddr(), httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
        databaseService.deleteDatabase(id);
    }
}