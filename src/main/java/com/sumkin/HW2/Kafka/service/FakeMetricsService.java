package com.sumkin.HW2.Kafka.service;

import com.sumkin.HW2.Kafka.model.FakeMetrics;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Log4j2
public class FakeMetricsService {


    @Value("${spring.kafka.my-topic}")
    private String topicName;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    public Properties initKafka() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return props;
    }


    public FakeMetrics sendFakeMetrics(FakeMetrics fakeMetrics) {
        Properties props = initKafka();
        String key = getAuthorizedUser();
                try (KafkaProducer<String, FakeMetrics> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<>(topicName, key, fakeMetrics));
            log.info("Метрика пользователя " + key + " успешно отправлена в Kafka топик.");
        } catch (Exception e) {
            System.err.println("Ошибка при отправке метрик: " + e.getMessage());
        }
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
