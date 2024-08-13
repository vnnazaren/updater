//package com.vnazarenko.updater.databasegroup.controller;
//
//import com.vnazarenko.updater.databasegroup.DatabaseGroupService;
//import com.vnazarenko.updater.databasegroup.model.DatabaseGroupPayload;
//import com.vnazarenko.updater.util.Marker;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * Класс-контроллер DatabaseGroup
// */
//@Slf4j
//@Validated
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/db_groups")
//public class DatabaseGroupController {
//    private final DatabaseGroupService databaseGroupService;
//
//    /**
//     * Создание группы БД
//     *
//     * @param databaseGroupPayload Тело запроса с DTO группы базы данных
//     * @return объект DTO с новой созданной группой базой данных
//     */
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public DatabaseGroupPayload createDatabaseGroup(@Validated(Marker.OnCreate.class) @RequestBody DatabaseGroupPayload databaseGroupPayload) {
//        log.info("POST /db_groups - %s".formatted(databaseGroupPayload));
//        return databaseGroupService.createDatabaseGroup(databaseGroupPayload);
//    }
//
//    /**
//     * Получение всех групп БД
//     *
//     * @return список с объектами DTO групп баз данных
//     */
//    @GetMapping
//    public List<DatabaseGroupPayload> readDatabaseGroups() {
//        log.info("GET /db_groups");
//        return databaseGroupService.readDatabaseGroups();
//    }
//
//    /**
//     * Получение группы БД по номеру
//     *
//     * @param id идентификатор группы базы данных
//     * @return объект DTO группы базы данных
//     */
//    @GetMapping("/{id}")
//    public DatabaseGroupPayload readDatabaseGroup(@PathVariable("id") Long id) {
//        log.info("GET /db_groups/%d".formatted(id));
//        return databaseGroupService.readDatabaseGroup(id);
//    }
//
//    /**
//     * Изменение группы БД
//     *
//     * @param id                   Идентификатор обновляемой группы базы данных
//     * @param databaseGroupPayload Тело запроса с DTO группы базы данных
//     * @return объект DTO группы базы данных с обновлёнными полями
//     */
//    @PatchMapping("/{id}")
//    public DatabaseGroupPayload updateDatabaseGroup(@PathVariable Long id,
//                                                    @Validated(Marker.OnUpdate.class) @RequestBody DatabaseGroupPayload databaseGroupPayload) {
//        log.info("PATCH /db_groups/%d - %s".formatted(id, databaseGroupPayload));
//        return databaseGroupService.updateDatabaseGroup(id, databaseGroupPayload);
//    }
//
//    /**
//     * Удаление группы БД
//     *
//     * @param id идентификатор группы базы данных
//     */
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteDatabaseGroup(@PathVariable("id") Long id) {
//        log.info("DELETE /db_groups/%d".formatted(id));
//        databaseGroupService.deleteDatabaseGroup(id);
//    }
//}