package com.sumkin.kafkaconsumer.service;

import com.sumkin.kafkaconsumer.model.FakeMetrics;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;

@Service
@Log4j2
public class ExtendedMetricsService {


    @Value("${spring.kafka.my-topic}")
    private String topicName;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public Properties initKafka() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return props;
    }


    @PostConstruct
    public void consumeFakeMetrics() {
        Properties props = initKafka();
        try (KafkaConsumer<String, FakeMetrics> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singleton(topicName));
            ConsumerRecord<String, FakeMetrics> record = consumer.poll(100).iterator().next();
            FakeMetrics fakeMetrics = record.value();
            log.info("Метрика пользователя " + " успешно добавлена в базу данных");
            log.info(fakeMetrics);
        } catch (Exception e) {
            System.err.println("Ошибка при получении метрик: " + e.getMessage());
        }
    }
}
