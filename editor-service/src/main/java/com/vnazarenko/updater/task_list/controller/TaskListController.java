package com.vnazarenko.updater.task_list.controller;

import com.vnazarenko.updater.task_list.TaskListService;
import com.vnazarenko.updater.task_list.model.TaskListPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер TaskList
 */
@Controller
@RequestMapping(("task_lists/{taskListId:\\d+}"))
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    /**
     * Описание модели
     *
     * @param taskListId идентификатор базы данных
     * @return Получение из локальной БД объекта с настройками базы данных
     */
    @ModelAttribute("task_list")
    public TaskListPayload taskList(@PathVariable("taskListId") Long taskListId) {
        return this.taskListService.readTaskList(taskListId);
    }

    /**
     * Получение настроек БД по номеру
     */
    @GetMapping
    public String getTaskList() {
        return "task_lists/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getTaskListEditPage() {
        return "task_lists/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateTaskList(@ModelAttribute(name = "task_list", binding = false) TaskListPayload taskList,
                                 @Validated(Marker.OnUpdate.class) TaskListPayload payload,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "task_lists/edit";
        } else {
            this.taskListService.updateTaskList(payload.id(), payload);
            return "redirect:/task_lists/%d".formatted(taskList.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    public String deleteTaskList(@ModelAttribute("task_list") TaskListPayload taskList) {
        this.taskListService.deleteTaskList(taskList.id());
        return "redirect:/task_lists/list";
    }
}