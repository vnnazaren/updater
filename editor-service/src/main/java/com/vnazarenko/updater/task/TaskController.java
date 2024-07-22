package com.vnazarenko.updater.task;

import com.vnazarenko.updater.task.model.TaskPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер Task
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    /**
     * Создание задачи
     *
     * @param taskPayload Тело запроса с DTO задачи
     * @return объект DTO с новой созданной задачей
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskPayload createTask(@Validated(Marker.OnCreate.class) @RequestBody TaskPayload taskPayload) {
        log.info("POST /tasks - %s".formatted(taskPayload));
        return taskService.createTask(taskPayload);
    }

    /**
     * Получение всех задач
     *
     * @return список с объектами DTO задач
     */
    @GetMapping
    public List<TaskPayload> readTasks() {
        log.info("GET /tasks");
        return taskService.readTasks();
    }

    /**
     * Получение задачи по номеру
     *
     * @param id идентификатор задачи
     * @return объект DTO задачи
     */
    @GetMapping("/{id}")
    public TaskPayload readTask(@PathVariable("id") Long id) {
        log.info("GET /tasks/%d".formatted(id));
        return taskService.readTask(id);
    }

    /**
     * Изменение задачи
     *
     * @param id          Идентификатор обновляемой задачи
     * @param taskPayload Тело запроса с DTO задачи
     * @return объект DTO задачи с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public TaskPayload updateTask(@PathVariable Long id,
                                  @Validated(Marker.OnUpdate.class) @RequestBody TaskPayload taskPayload) {
        log.info("PATCH /tasks/%d - %s".formatted(id, taskPayload));
        return taskService.updateTask(id, taskPayload);
    }

    /**
     * Удаление задачи
     *
     * @param id идентификатор задачи
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        log.info("DELETE /tasks/%d".formatted(id));
        taskService.deleteTask(id);
    }
}