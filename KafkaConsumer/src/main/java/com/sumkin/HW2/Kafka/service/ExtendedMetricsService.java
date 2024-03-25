package com.sumkin.HW2.Kafka.service;

import com.sumkin.HW2.Kafka.repository.ExtendedMetricsRepository;
import com.sumkin.HW2.Kafka.model.ExtendedMetrics;
import com.sumkin.HW2.Kafka.model.FakeMetrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class ExtendedMetricsService {

    private final ExtendedMetricsRepository extendedMetricsRepository;

    @KafkaListener(topics = "metrics-topic", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void listenGroup(ConsumerRecord<String, FakeMetrics> consumerRecord) {
        FakeMetrics fakeMetrics = consumerRecord.value();
        log.info("Consumed message from topic metrics-topic: " + fakeMetrics);
        ExtendedMetrics extendedMetrics = ExtendedMetrics.builder()
                .kafkaId(consumerRecord.offset())
                .authorizedUser(consumerRecord.key())
                .timestamp(Instant.ofEpochMilli(consumerRecord.timestamp()))
                .temperature(fakeMetrics.getTemperature())
                .humidity(fakeMetrics.getHumidity())
                .comment(fakeMetrics.getComment())
                .build();

        extendedMetricsRepository.save(extendedMetrics);

        log.info("Extended metrics: " + extendedMetrics);
    }

    @Transactional(readOnly = true)
    public List<ExtendedMetrics> getAllMetrics(int page, int size, String sort) {
        String[] sortParams = sort.split(",");
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        List<ExtendedMetrics> products = extendedMetricsRepository.findAll(pageable).getContent();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No metrics found");
        }
        return products;
    }

    @Transactional(readOnly = true)
    public ExtendedMetrics getProductById(Long id) {
        return extendedMetricsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Metrics not found"));
    }

    public List<ExtendedMetrics> getMetricsOverTemperature(int page, int size, String sort, Integer minTemperature) {
        String[] sortParams = sort.split(",");
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortObj);
        List<ExtendedMetrics> products = extendedMetricsRepository.getMetricsOverTemperature(minTemperature, pageable);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No metrics found");
        }
        return products;
    }
}

