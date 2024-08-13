package com.vnazarenko.updater.task_list.controller;

import com.vnazarenko.updater.stat.StatClient;
import com.vnazarenko.updater.stat.model.StatIn;
import com.vnazarenko.updater.task_list.TaskListService;
import com.vnazarenko.updater.task_list.model.TaskListPayload;
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
 * Класс-контроллер Task_list
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("task_lists")
public class TaskListsController {

    private final TaskListService taskListService;
    private final StatClient statClient;

    /**
     * Получение страницы со списком всех БД
     */
    @GetMapping("list")
    public String getTaskListsList(Model model) {
        model.addAttribute("task_lists", this.taskListService.readTaskLists());
        return "task_lists/list";
    }

    /**
     * Получение страницы для создания новой БД
     */
    @GetMapping("create")
    public String getNewTaskListPage() {
        return "task_lists/create";
    }

    /**
     * Создание настроек БД
     */
    @PostMapping("create")
    public String createTaskList(@Validated(Marker.OnCreate.class) TaskListPayload payload,
                                 BindingResult bindingResult,
                                 HttpServletRequest httpServletRequest,
                                 Model model) {

        log.info("POST /task_lists/create - %s".formatted(payload));
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
            return "task_lists/create";
        } else {
            TaskListPayload taskList = this.taskListService.createTaskList(payload);
            return "redirect:/task_lists/%d".formatted(taskList.id());
        }
    }
}