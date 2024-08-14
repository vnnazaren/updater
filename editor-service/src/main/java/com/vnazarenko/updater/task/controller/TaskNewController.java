package com.vnazarenko.updater.task.controller;

import com.vnazarenko.updater.task.TaskService;
import com.vnazarenko.updater.task.model.TaskPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер Task
 */
@Controller
@RequestMapping(("tasks/{taskId:\\d+}"))
@RequiredArgsConstructor
public class TaskNewController {

    private final TaskService taskService;

    /**
     * Описание модели
     *
     * @param taskId идентификатор базы данных
     * @return Получение из локальной БД объекта с настройками базы данных
     */
    @ModelAttribute("task")
    public TaskPayload task(@PathVariable("taskId") Long taskId) {
        return this.taskService.readTask(taskId);
    }

    /**
     * Получение настроек БД по номеру
     */
    @GetMapping
    public String getTask() {
        return "tasks/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getTaskEditPage() {
        return "tasks/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateTask(@ModelAttribute(name = "task", binding = false) TaskPayload task,
                             @Validated(Marker.OnUpdate.class) TaskPayload payload,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "tasks/edit";
        } else {
            this.taskService.updateTask(payload.id(), payload);
            return "redirect:/tasks/%d".formatted(task.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    public String deleteTask(@ModelAttribute("task") TaskPayload task) {
        this.taskService.deleteTask(task.id());
        return "redirect:/tasks/list";
    }
}