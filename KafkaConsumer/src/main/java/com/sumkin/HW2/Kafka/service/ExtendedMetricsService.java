package com.sumkin.HW2.Kafka.service;

import com.sumkin.HW2.Kafka.repository.ExtendedMetricsRepository;
import com.sumkin.HW2.Kafka.model.ExtendedMetrics;
import com.sumkin.HW2.Kafka.model.FakeMetrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


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

}

