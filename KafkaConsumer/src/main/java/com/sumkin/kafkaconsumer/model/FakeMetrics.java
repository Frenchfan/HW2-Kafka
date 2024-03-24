package com.sumkin.kafkaconsumer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Schema(description = "Fake metrics")
public class FakeMetrics {

    @Schema(example = "10")
    private int temperature;
    @Schema(example = "20")
    private int humidity;
    @Schema(example = "Testing fake metrics")
    private String comment;

}
