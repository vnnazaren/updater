package com.vnazarenko.updater.database_group.controller;

import com.vnazarenko.updater.database_group.DatabaseGroupService;
import com.vnazarenko.updater.database_group.model.DatabaseGroupPayload;
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
 * Класс-контроллер DatabaseGroup
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("database_groups")
public class DatabaseGroupsNewController {

    private final DatabaseGroupService databaseGroupService;
    private final StatClient statClient;

    /**
     * Получение страницы со списком всех БД
     */
    @GetMapping("list")
    public String getDatabaseGroupsList(Model model) {
        model.addAttribute("database_groups", this.databaseGroupService.readDatabaseGroups());
        return "db_groups/list";
    }

    /**
     * Получение страницы для создания новой БД
     */
    @GetMapping("create")
    public String getNewTaskListPage() {
        return "db_groups/create";
    }

    /**
     * Создание настроек БД
     */
    @PostMapping("create")
    public String createTaskList(@Validated(Marker.OnCreate.class) DatabaseGroupPayload payload,
                                 BindingResult bindingResult,
                                 HttpServletRequest httpServletRequest,
                                 Model model) {

        log.info("POST /database_groups/create - %s".formatted(payload));
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
            return "db_groups/create";
        } else {
            DatabaseGroupPayload databaseGroupPayload = this.databaseGroupService.createDatabaseGroup(payload);
            return "redirect:/database_groups/%d".formatted(databaseGroupPayload.id());
        }
    }
}