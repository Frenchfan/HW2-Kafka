package com.sumkin.HW2.Kafka.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
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
    private Instant timestamp;
    @Schema(description = "Temperature")
    private int temperature;
    @Schema(description = "Humidity")
    private int humidity;
    @Schema(description = "Comment")
    private String comment;
}
