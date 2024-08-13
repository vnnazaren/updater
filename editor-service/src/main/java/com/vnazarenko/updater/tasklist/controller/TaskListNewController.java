package com.vnazarenko.updater.tasklist.controller;

import com.vnazarenko.updater.tasklist.TaskListService;
import com.vnazarenko.updater.tasklist.model.TaskListPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер Tasklist
 */
@Controller
@RequestMapping(("tasklists/{tasklistId:\\d+}"))
@RequiredArgsConstructor
public class TaskListNewController {

    private final TaskListService taskListService;

    /**
     * Описание модели
     *
     * @param taskListId идентификатор базы данных
     * @return Получение из локальной БД объекта с настройками базы данных
     */
    @ModelAttribute("tasklist")
    public TaskListPayload taskList(@PathVariable("tasklistId") Long taskListId) {
        return this.taskListService.readTaskList(taskListId);
    }

    /**
     * Получение настроек БД по номеру
     */
    @GetMapping
    public String getTaskList() {
        return "tasklists/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getTaskListEditPage() {
        return "tasklists/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateTaskList(@ModelAttribute(name = "tasklist", binding = false) TaskListPayload tasklist,
                                 @Validated(Marker.OnUpdate.class) TaskListPayload payload,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "tasklists/edit";
        } else {
            this.taskListService.updateTaskList(payload.id(), payload);
            return "redirect:/tasklists/%d".formatted(tasklist.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteTaskList(@ModelAttribute("tasklist") TaskListPayload tasklist) {
        this.taskListService.deleteTaskList(tasklist.id());
        return "redirect:/tasklists/list";
    }
}