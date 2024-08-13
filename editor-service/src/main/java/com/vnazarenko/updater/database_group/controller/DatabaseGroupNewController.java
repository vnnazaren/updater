package com.vnazarenko.updater.database_group.controller;

import com.vnazarenko.updater.database_group.DatabaseGroupService;
import com.vnazarenko.updater.database_group.model.DatabaseGroupPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер DatabaseGroup
 */
@Controller
@RequestMapping(("database_groups/{databaseGroupId:\\d+}"))
@RequiredArgsConstructor
public class DatabaseGroupNewController {

    private final DatabaseGroupService databaseGroupService;

    /**
     * Описание модели
     *
     * @param databaseGroupId идентификатор группы баз данных
     * @return Получение из локальной БД объекта с группой баз данных
     */
    @ModelAttribute("database_group")
    public DatabaseGroupPayload databaseGroup(@PathVariable("databaseGroupId") Long databaseGroupId) {
        return this.databaseGroupService.readDatabaseGroup(databaseGroupId);
    }

    /**
     * Получение группы БД по номеру
     */
    @GetMapping
    public String getDatabaseGroup() {
        return "db_groups/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getDatabaseGroupEditPage() {
        return "db_groups/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateDatabaseGroup(@ModelAttribute(name = "database_group", binding = false) DatabaseGroupPayload databaseGroup,
                                      @Validated(Marker.OnUpdate.class) DatabaseGroupPayload payload,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "db_groups/edit";
        } else {
            this.databaseGroupService.updateDatabaseGroup(payload.id(), payload);
            return "redirect:/database_groups/%d".formatted(databaseGroup.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    public String deleteDatabaseGroup(@ModelAttribute("database_group") DatabaseGroupPayload databaseGroup) {
        this.databaseGroupService.deleteDatabaseGroup(databaseGroup.id());
        return "redirect:/database_groups/list";
    }
}