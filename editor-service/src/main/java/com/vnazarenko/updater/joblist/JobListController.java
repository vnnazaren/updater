package com.vnazarenko.updater.joblist;

import com.vnazarenko.updater.joblist.model.JobListPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер JobList
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/joblists")
public class JobListController {
    private final JobListService jobListService;

    /**
     * Создание списка задач на выполнение
     *
     * @param jobListPayload Тело запроса с DTO списка задач на выполнение
     * @return объект DTO с новым созданным списком задач на выполнение
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobListPayload createJobList(@Validated(Marker.OnCreate.class) @RequestBody JobListPayload jobListPayload) {
        log.info("POST /joblists - %s".formatted(jobListPayload));
        return jobListService.createJobList(jobListPayload);
    }

    /**
     * Получение всех списков задач на выполнение
     *
     * @return список с объектами DTO списков на выполнение
     */
    @GetMapping
    public List<JobListPayload> readJobLists() {
        log.info("GET /joblists");
        return jobListService.readJobLists();
    }

    /**
     * Получение списка задач на выполнение
     *
     * @param id идентификатор списка задач на выполнение
     * @return объект DTO списка задач на выполнение
     */
    @GetMapping("/{id}")
    public JobListPayload readJobList(@PathVariable("id") Long id) {
        log.info("GET /joblists/%d".formatted(id));
        return jobListService.readJobList(id);
    }

    /**
     * Изменение списка задач на выполнение
     *
     * @param id         Идентификатор обновляемого списка задач на выполнение
     * @param jobListDto Тело запроса с DTO списка задач на выполнение
     * @return объект DTO списка задач на выполнение с обновлёнными полями
     */
    @PatchMapping("/{id}")
    public JobListPayload updateJobList(@PathVariable Long id,
                                        @Validated(Marker.OnUpdate.class) @RequestBody JobListPayload jobListDto) {
        log.info("PATCH /joblists/%d - %s".formatted(id, jobListDto));
        return jobListService.updateJobList(id, jobListDto);
    }

    /**
     * Удаление списка задач на выполнение
     *
     * @param id идентификатор списка задач на выполнение
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJobList(@PathVariable("id") Long id) {
        log.info("DELETE /joblists/%d".formatted(id));
        jobListService.deleteJobList(id);
    }
}