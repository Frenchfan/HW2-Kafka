package com.sumkin.HW2.Kafka.repository;

import com.sumkin.HW2.Kafka.model.ExtendedMetrics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExtendedMetricsRepository extends PagingAndSortingRepository<ExtendedMetrics, Long>,
        JpaRepository<ExtendedMetrics, Long> {

    @Query("SELECT m FROM extended_metrics m WHERE m.temperature >= :minTemperature")
    List<ExtendedMetrics> getMetricsOverTemperature(@Param("minTemperature") Integer minTemperature, Pageable pageable);

}
