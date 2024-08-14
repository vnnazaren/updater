package com.vnazarenko.updater.task.controller;

import com.vnazarenko.updater.stat.StatClient;
import com.vnazarenko.updater.stat.model.StatIn;
import com.vnazarenko.updater.task.TaskService;
import com.vnazarenko.updater.task.model.TaskPayload;
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
 * Класс-контроллер Task
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TasksNewController {

    private final TaskService taskService;
    private final StatClient statClient;

    /**
     * Получение страницы со списком всех БД
     */
    @GetMapping("list")
    public String getTasksList(Model model) {
        model.addAttribute("tasks", this.taskService.readTasks());
        return "tasks/list";
    }

    /**
     * Получение страницы для создания новой БД
     */
    @GetMapping("create")
    public String getNewTaskPage() {
        return "tasks/create";
    }

    /**
     * Создание настроек БД
     */
    @PostMapping("create")
    public String createTask(@Validated(Marker.OnCreate.class) TaskPayload payload,
                             BindingResult bindingResult,
                             HttpServletRequest httpServletRequest,
                             Model model) {

        log.info("POST /tasks/create - %s".formatted(payload));
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
            return "tasks/create";
        } else {
            TaskPayload task = this.taskService.createTask(payload);
            return "redirect:/tasks/%d".formatted(task.id());
        }
    }
}