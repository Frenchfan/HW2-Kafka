package com.sumkin.HW2.Kafka.metrics;

import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Set;


@Component
@Slf4j
public class MetricsCollector {

//    @Scheduled(fixedRate = 5000)
//    public static void testMetrics() {
//
//        MetricsEndpoint metricsEndpoint = new MetricsEndpoint(Metrics.globalRegistry);
//        MetricsEndpoint.MetricDescriptor metric = metricsEndpoint.metric("application.ready.time", null);
//        List<MetricsEndpoint.Sample> samples = metric.getMeasurements();
//        samples.forEach(s -> log.info(s.getStatistic().name() + ": " + s.getValue()));
//
//        log.info("Metrics: {}", metricsNames);
//        System.out.println(metricsNames);
//        for (String metricName : metricsNames) {
//            Object metricValue = Objects.requireNonNull(Metrics.globalRegistry.find("application.ready.time").gauge()).value();
//            if (metricValue == null) {
//                continue;
//            }
//
//            System.out.println(metricName + ": " + metricValue);
//    }
}

