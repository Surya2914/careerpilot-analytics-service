package com.example.analytics_service.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStatistics() {

        Map<String, Object> statistics = new HashMap<>();

        List<?> jobs = restTemplate.getForObject(
                "http://localhost:8082/api/jobs",
                List.class
        );

        List<?> applications = restTemplate.getForObject(
                "http://localhost:8083/api/applications",
                List.class
        );

        statistics.put("totalJobs", jobs == null ? 0 : jobs.size());
        statistics.put("totalApplications",
                applications == null ? 0 : applications.size());

        return statistics;
    }
}
