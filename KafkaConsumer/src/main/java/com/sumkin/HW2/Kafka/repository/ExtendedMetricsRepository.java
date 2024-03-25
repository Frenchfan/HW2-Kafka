package com.sumkin.HW2.Kafka.repository;

import com.sumkin.HW2.Kafka.model.ExtendedMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExtendedMetricsRepository extends PagingAndSortingRepository<ExtendedMetrics, Long>,
        JpaRepository<ExtendedMetrics, Long> {
}
