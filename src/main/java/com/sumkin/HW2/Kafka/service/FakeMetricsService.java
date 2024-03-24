package com.sumkin.HW2.Kafka.service;

import com.sumkin.HW2.Kafka.model.FakeMetrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Log4j2
public class FakeMetricsService {


    @Value("${spring.kafka.my-topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, FakeMetrics> kafkaTemplate;

    public FakeMetrics sendFakeMetrics(FakeMetrics fakeMetrics, String typeId) {
        ProducerRecord<String, FakeMetrics> record = new ProducerRecord<>(topicName, getAuthorizedUser(), fakeMetrics);
        record.headers().add(new RecordHeader("__TypeId__", typeId.getBytes()));
        kafkaTemplate.send(record);
        log.info("Метрика пользователя " + getAuthorizedUser() + " успешно отправлена в Kafka");
        return fakeMetrics;
    }

    private String getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return "Anonymous";
    }
}
