package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiter {

    private final Map<String, Instant> requestTimes = new ConcurrentHashMap<>();
    private static final long LIMIT_SECONDS = 20 * 60; // 20 minutes

    public boolean isAllowed(String ip) {
        Instant now = Instant.now();
        Instant lastRequest = requestTimes.get(ip);

        if (lastRequest == null || now.isAfter(lastRequest.plusSeconds(LIMIT_SECONDS))) {
            requestTimes.put(ip, now); // update last request time
            return true; // allow
        }

        return false; // blocked
    }
}

