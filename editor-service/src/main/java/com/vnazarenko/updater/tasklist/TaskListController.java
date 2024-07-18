package com.vnazarenko.updater.tasklist;

import com.vnazarenko.updater.tasklist.model.TaskListDto;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер TaskList
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasklists")
public class TaskListController {
    private final TaskListService taskListService;

    /**
     * Создание списка задач
     *
     * @param taskListDto Тело запроса с DTO списка задач
     * @return объект DTO с новым созданным списком задач
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskListDto createTaskList(@Validated(Marker.OnCreate.class) @RequestBody TaskListDto taskListDto) {
        log.info("POST /tasklists - {%s}".formatted(taskListDto));
        return taskListService.createTaskList(taskListDto);
    }

    /**
     * Получение всех списков задач
     *
     * @return список с объектами DTO списков задачи
     */
    @GetMapping
    public List<TaskListDto> readTaskLists() {
        log.info("GET /tasklists");
        return taskListService.readTaskLists();
    }

    /**
     * Получение списка задач по ID
     *
     * @param id идентификатор списка задач
     * @return объект DTO списка задач
     */
    @GetMapping("/{id}")
    public TaskListDto readTaskList(@PathVariable("id") Long id) {
        log.info("GET /tasklists/%d".formatted(id));
        return taskListService.readTaskList(id);
    }

    /**
     * Изменение списка задач
     *
     * @param id          Идентификатор обновляемого списка задач
     * @param taskListDto Тело запроса с DTO списка задач
     * @return объект DTO списка задач с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public TaskListDto updateScenario(@PathVariable Long id,
                                      @Validated(Marker.OnUpdate.class) @RequestBody TaskListDto taskListDto) {
        log.info("PATCH /tasklists/%d - {%s}".formatted(id, taskListDto));
        return taskListService.updateTaskList(id, taskListDto);
    }

    /**
     * Удаление списка задач
     *
     * @param id идентификатор списка задач
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskList(@PathVariable("id") Long id) {
        log.info("DELETE /tasklists/%d".formatted(id));
        taskListService.deleteTaskList(id);
    }
}