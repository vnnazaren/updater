package com.vnazarenko.updater.database.controller;

import com.vnazarenko.updater.database.DatabaseService;
import com.vnazarenko.updater.database.model.DatabasePayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер Database
 */
@Controller
@RequestMapping(("databases/{databaseId:\\d+}"))
@RequiredArgsConstructor
public class DatabaseController {

    private final DatabaseService databaseService;

    /**
     * Описание модели
     *
     * @param databaseId идентификатор базы данных
     * @return Получение из локальной БД объекта с настройками базы данных
     */
    @ModelAttribute("database")
    public DatabasePayload database(@PathVariable("databaseId") Long databaseId) {
        return this.databaseService.readDatabase(databaseId);
    }

    /**
     * Получение настроек БД по номеру
     */
    @GetMapping
    public String getDatabase() {
        return "databases/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getDatabaseEditPage() {
        return "databases/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateDatabase(@ModelAttribute(name = "database", binding = false) DatabasePayload database,
                                 @Validated(Marker.OnUpdate.class) DatabasePayload payload,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "databases/edit";
        } else {
            this.databaseService.updateDatabase(payload.id(), payload);
            return "redirect:/databases/%d".formatted(database.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    public String deleteDatabase(@ModelAttribute("database") DatabasePayload database) {
        this.databaseService.deleteDatabase(database.id());
        return "redirect:/databases/list";
    }
}