package com.vnazarenko.updater.util;

import com.vnazarenko.updater.client.StatClient;
import com.vnazarenko.updater.dto.HitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class StatSaver {
    private final StatClient statClient;

    public void saveHit(String ip, String uri, String httpMethod) {
        statClient.saveHit(HitDto.builder()
                .app("editor-service")
                .ip(ip)
                .uri(uri)
                .httpMethod(httpMethod)
                .timestamp(LocalDateTime.now())
                .build());
    }
}