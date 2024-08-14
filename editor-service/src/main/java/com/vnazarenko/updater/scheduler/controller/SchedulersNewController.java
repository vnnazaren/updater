package com.vnazarenko.updater.scheduler.controller;

import com.vnazarenko.updater.scheduler.SchedulerService;
import com.vnazarenko.updater.scheduler.model.SchedulerPayload;
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
 * Класс-контроллер Scheduler
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("schedulers")
public class SchedulersNewController {

    private final SchedulerService schedulerService;
    private final StatClient statClient;

    /**
     * Получение страницы со списком всех БД
     */
    @GetMapping("list")
    public String getSchedulersList(Model model) {
        model.addAttribute("schedulers", this.schedulerService.readSchedulers());
        return "schedulers/list";
    }

    /**
     * Получение страницы для создания новой БД
     */
    @GetMapping("create")
    public String getNewSchedulerPage() {
        return "schedulers/create";
    }

    /**
     * Создание настроек БД
     */
    @PostMapping("create")
    public String createScheduler(@Validated(Marker.OnCreate.class) SchedulerPayload payload,
                                  BindingResult bindingResult,
                                  HttpServletRequest httpServletRequest,
                                  Model model) {

        log.info("POST /schedulers/create - %s".formatted(payload));
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
            return "schedulers/create";
        } else {
            SchedulerPayload scheduler = this.schedulerService.createScheduler(payload);
            return "redirect:/schedulers/%d".formatted(scheduler.id());
        }
    }
}