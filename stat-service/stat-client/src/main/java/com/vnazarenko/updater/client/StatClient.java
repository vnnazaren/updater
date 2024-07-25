package com.vnazarenko.updater.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.vnazarenko.updater.dto.HitDto;
import com.vnazarenko.updater.dto.StatDto;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StatClient {
    private final RestTemplate rest;

    @Value("${stats-server.url}")
    private String serverUrl;

    public void saveHit(HitDto hitDto) {
        rest.postForLocation(serverUrl.concat("/hit"), hitDto);
    }

    public List<StatDto> getStats(String start, String end, List<String> uris, Boolean unique) {
        Map<String, Object> parameters = Map.of(
                "start", start,
                "end", end,
                "uris", uris,
                "unique", unique);

        StatDto[] statistics = rest.getForObject(
                serverUrl.concat("/stats?start={start}&end={end}&uris={uris}&unique={unique}"),
                StatDto[].class,
                parameters);

        if (statistics == null) {
            return List.of();
        }

        return List.of(statistics);
    }
}