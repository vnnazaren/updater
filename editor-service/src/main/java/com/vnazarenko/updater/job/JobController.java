package com.vnazarenko.updater.job;

import com.vnazarenko.updater.job.model.JobDto;
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
     * @param jobDto Тело запроса с DTO задачи на выполнение
     * @return объект DTO с новой созданной задачей на выполнение
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobDto createJob(@Validated(Marker.OnCreate.class) @RequestBody JobDto jobDto) {
        log.info("POST /jobs - {%s}".formatted(jobDto));
        return jobService.createJob(jobDto);
    }

    /**
     * Получение всех задач на выполнение
     *
     * @return список с объектами DTO задач на выполнение
     */
    @GetMapping
    public List<JobDto> readJobs() {
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
    public JobDto readJob(@PathVariable("id") Long id) {
        log.info("GET /jobs/%d".formatted(id));
        return jobService.readJob(id);
    }

    /**
     * Изменение задачи на выполнение
     *
     * @param id     Идентификатор задачи на выполнение
     * @param jobDto Тело запроса с DTO задачи на выполнение
     * @return объект DTO задачи на выполнение
     */
    @PatchMapping("/{id}")
    public JobDto updateJob(@PathVariable Long id,
                            @Validated(Marker.OnUpdate.class) @RequestBody JobDto jobDto) {
        log.info("PATCH /jobs/%d - %s".formatted(id, jobDto));
        return jobService.updateJob(id, jobDto);
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