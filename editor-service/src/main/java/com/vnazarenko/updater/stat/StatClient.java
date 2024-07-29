package com.vnazarenko.updater.stat;

import com.vnazarenko.updater.stat.model.StatIn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class StatClient {
    private final RestTemplate restTemplate;

    @Value("${stats-server.url}")
    private String serverUrl;

    public void saveHit(StatIn statIn) {
        restTemplate.postForLocation(serverUrl.concat("/hit"), statIn);
    }
}