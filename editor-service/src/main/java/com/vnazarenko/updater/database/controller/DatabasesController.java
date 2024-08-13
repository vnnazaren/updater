package com.vnazarenko.updater.database.controller;

import com.vnazarenko.updater.database.DatabaseService;
import com.vnazarenko.updater.database.model.DatabasePayload;
import com.vnazarenko.updater.stat.StatClient;
import com.vnazarenko.updater.stat.model.StatIn;
import com.vnazarenko.updater.util.Marker;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * Класс-контроллер Database
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("databases")
public class DatabasesController {

    private final DatabaseService databaseService;
    private final StatClient statClient;

    /**
     * Получение страницы со списком всех БД
     */
    @GetMapping("list")
    public String getDatabasesList(Model model) {
        model.addAttribute("databases", this.databaseService.readDatabases());
        return "databases/list";
    }

    /**
     * Получение страницы для создания новой БД
     */
    @GetMapping("create")
    public String getNewDatabasePage() {
        return "databases/create";
    }

    /**
     * Создание настроек БД
     */
    @PostMapping("create")
    public String createDatabase(@Validated(Marker.OnCreate.class) DatabasePayload payload,
                                 BindingResult bindingResult,
                                 HttpServletRequest httpServletRequest,
                                 Model model) {

        log.info("POST /databases/create - %s".formatted(payload));
        statClient.saveHit(new StatIn("editor-service",
                httpServletRequest.getRemoteAddr(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                LocalDateTime.now()));

        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "databases/create";
        } else {
            DatabasePayload database = this.databaseService.createDatabase(payload);
            return "redirect:/databases/%d".formatted(database.id());
        }
    }
}