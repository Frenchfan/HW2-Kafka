package com.sumkin.HW2.Kafka.controller;

import com.sumkin.HW2.Kafka.model.FakeMetrics;
import com.sumkin.HW2.Kafka.service.FakeMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metrics")
@Log4j2
public class MetricsController {

    private final FakeMetricsService fakeMetricsService;

    public MetricsController(FakeMetricsService fakeMetricsService) {
        this.fakeMetricsService = fakeMetricsService;
    }

    @PostMapping
    @Operation(summary = "Create fake metrics")
    public FakeMetrics postMetrics(@RequestBody FakeMetrics fakeMetrics) {
        log.info("Created fake metrics: {}", fakeMetrics);
        return fakeMetricsService.sendFakeMetrics(fakeMetrics, "com.sumkin.kafkaconsumer.model.FakeMetrics");
    }
}
