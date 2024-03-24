package com.sumkin.kafkaconsumer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity(name = "extended_metrics")
@Schema(description = "Extended metrics")
public class ExtendedMetrics {

    @Schema(description = "Id")
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Kafka offset id")
    @Column(name = "kafka_id")
    private Long kafkaId;
    @Schema(description = "Authorized user - key from Kafka")
    @Column(name = "authorized_user")
    private String authorizedUser;
    @Schema(description = "Timestamp")
    private String timestamp;
    @Schema(description = "Temperature")
    private int temperature;
    @Schema(description = "Humidity")
    private int humidity;
    @Schema(description = "Comment")
    private String comment;
}
