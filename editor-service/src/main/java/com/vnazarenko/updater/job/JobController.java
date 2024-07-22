package com.vnazarenko.updater.job;

import com.vnazarenko.updater.job.model.JobPayload;
import com.vnazarenko.updater.util.Marker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер Job
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    /**
     * Создание сценария
     *
     * @param jobPayload Тело запроса с DTO задачи на выполнение
     * @return объект DTO с новой созданной задачей на выполнение
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobPayload createJob(@Validated(Marker.OnCreate.class) @RequestBody JobPayload jobPayload) {
        log.info("POST /jobs - %s".formatted(jobPayload));
        return jobService.createJob(jobPayload);
    }

    /**
     * Получение всех задач на выполнение
     *
     * @return список с объектами DTO задач на выполнение
     */
    @GetMapping
    public List<JobPayload> readJobs() {
        log.info("GET /jobs");
        return jobService.readJobs();
    }

    /**
     * Получение всех задач на выполнение
     *
     * @param id идентификатор задачи на выполнение
     * @return объект DTO задачи на выполнение
     */
    @GetMapping("/{id}")
    public JobPayload readJob(@PathVariable("id") Long id) {
        log.info("GET /jobs/%d".formatted(id));
        return jobService.readJob(id);
    }

    /**
     * Изменение задачи на выполнение
     *
     * @param id         Идентификатор задачи на выполнение
     * @param jobPayload Тело запроса с DTO задачи на выполнение
     * @return объект DTO задачи на выполнение
     */
    @PatchMapping("/{id}")
    public JobPayload updateJob(@PathVariable Long id,
                                @Validated(Marker.OnUpdate.class) @RequestBody JobPayload jobPayload) {
        log.info("PATCH /jobs/%d - %s".formatted(id, jobPayload));
        return jobService.updateJob(id, jobPayload);
    }

    /**
     * Удаление задачи на выполнение
     *
     * @param id идентификатор задачи на выполнение
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable("id") Long id) {
        log.info("DELETE /jobs/%d".formatted(id));
        jobService.deleteJob(id);
    }
}