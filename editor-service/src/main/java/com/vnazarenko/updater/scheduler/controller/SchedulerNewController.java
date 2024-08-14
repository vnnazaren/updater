package com.vnazarenko.updater.scheduler.controller;

import com.vnazarenko.updater.scheduler.SchedulerService;
import com.vnazarenko.updater.scheduler.model.SchedulerPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс-контроллер Scheduler
 */
@Controller
@RequestMapping(("schedulers/{schedulerId:\\d+}"))
@RequiredArgsConstructor
public class SchedulerNewController {

    private final SchedulerService schedulerService;

    /**
     * Описание модели
     *
     * @param schedulerId идентификатор базы данных
     * @return Получение из локальной БД объекта с настройками базы данных
     */
    @ModelAttribute("scheduler")
    public SchedulerPayload scheduler(@PathVariable("schedulerId") Long schedulerId) {
        return this.schedulerService.readScheduler(schedulerId);
    }

    /**
     * Получение настроек БД по номеру
     */
    @GetMapping
    public String getScheduler() {
        return "schedulers/read";
    }

    /**
     * Получение страницы для изменения настроек БД
     */
    @GetMapping("edit")
    public String getSchedulerEditPage() {
        return "schedulers/edit";
    }

    /**
     * Изменение настроек БД
     */
    @PostMapping("edit")
    public String updateScheduler(@ModelAttribute(name = "scheduler", binding = false) SchedulerPayload scheduler,
                                  @Validated(Marker.OnUpdate.class) SchedulerPayload payload,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "schedulers/edit";
        } else {
            this.schedulerService.updateScheduler(payload.id(), payload);
            return "redirect:/schedulers/%d".formatted(scheduler.id());
        }
    }

    /**
     * Удаление настроек БД
     */
    @PostMapping("delete")
    public String deleteScheduler(@ModelAttribute("scheduler") SchedulerPayload scheduler) {
        this.schedulerService.deleteScheduler(scheduler.id());
        return "redirect:/schedulers/list";
    }
}