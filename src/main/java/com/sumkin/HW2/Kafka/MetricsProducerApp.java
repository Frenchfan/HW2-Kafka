package com.sumkin.HW2.Kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class MetricsProducerApp {

	public static void main(String[] args) {
		SpringApplication.run(MetricsProducerApp.class, args);
	}

}
