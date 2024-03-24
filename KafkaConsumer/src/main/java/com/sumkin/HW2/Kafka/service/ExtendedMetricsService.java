package com.sumkin.HW2.Kafka.service;

import com.sumkin.HW2.Kafka.model.FakeMetrics;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@RequiredArgsConstructor
public class ExtendedMetricsService {

    @KafkaListener(topics = "metrics-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(FakeMetrics message) {
        log.info("Consumed message from topic metrics-topic: " + message);
    }

    @PostConstruct
    public void test() {
        listenGroupFoo(new FakeMetrics());
    }

}

