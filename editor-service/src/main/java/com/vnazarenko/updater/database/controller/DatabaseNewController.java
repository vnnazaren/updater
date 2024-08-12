package com.vnazarenko.updater.database.controller;

import com.vnazarenko.updater.database.DatabaseService;
import com.vnazarenko.updater.database.model.DatabasePayload;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(("databases/{databaseId:\\d+}"))
@RequiredArgsConstructor
public class DatabaseNewController {

    private final DatabaseService databaseService;

    @ModelAttribute("database")
    public DatabasePayload database(@PathVariable("databaseId") Long databaseId) {
        return this.databaseService.readDatabase(databaseId);
    }

    @GetMapping
    public String getDatabase() {
        return "databases/read";
    }

    @GetMapping("edit")
    public String getDatabaseEditPage() {
        return "databases/edit";
    }

    @PostMapping("edit")
    public String updateDatabase(@ModelAttribute(name = "database", binding = false) DatabasePayload database,
                                 @Valid DatabasePayload payload,
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

    @PostMapping("delete")
    public String deleteDatabase(@ModelAttribute("database") DatabasePayload database) {
        this.databaseService.deleteDatabase(database.id());
        return "redirect:/databases/list";
    }
}