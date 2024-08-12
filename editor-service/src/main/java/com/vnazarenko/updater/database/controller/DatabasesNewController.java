package com.vnazarenko.updater.database.controller;

import com.vnazarenko.updater.database.DatabaseService;
import com.vnazarenko.updater.database.model.DatabasePayload;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("databases")
public class DatabasesNewController {

    private final DatabaseService databaseService;

    @GetMapping("list")
    public String getDatabasesList(Model model) {
        model.addAttribute("databases", this.databaseService.readDatabases());
        return "databases/list";
    }

    @GetMapping("create")
    public String getNewDatabasePage() {
        return "databases/create";
    }

    @PostMapping("create")
    public String createDatabase(@Valid DatabasePayload payload,
                                 BindingResult bindingResult,
                                 Model model) {
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