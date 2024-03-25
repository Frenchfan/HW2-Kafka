package com.sumkin.HW2.Kafka.controller;

import com.sumkin.HW2.Kafka.model.ExtendedMetrics;
import com.sumkin.HW2.Kafka.service.ExtendedMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metrics")
@Tag(name = "Extended Metrics Controller", description = "Metrics API")
@RequiredArgsConstructor
@Slf4j
public class ExtendedMetricsController {

    private final ExtendedMetricsService extendedMetricsService;

    @Operation(summary = "Find all Metrics with pagination and sorting")
    @GetMapping
    public List<ExtendedMetrics> findAllMetrics(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "id,asc") String sort,
                                                @RequestParam(required = false) Integer minTemperature) {
        log.info("Finding all metrics with pagination and sorting, page = " + page + ", " +
                "size = " + size + ", sort = " + sort);
        if (minTemperature != null) {
            return extendedMetricsService.getMetricsOverTemperature(page, size, sort, minTemperature);
        }
        return extendedMetricsService.getAllMetrics(page, size, sort);
    }

    @Operation(summary = "Find metrics by id")
    @GetMapping("/{id}")
    public ExtendedMetrics findProductById(@PathVariable Long id) {
        log.info("Finding metrics by id: " + id);
        return extendedMetricsService.getProductById(id);
    }
}
