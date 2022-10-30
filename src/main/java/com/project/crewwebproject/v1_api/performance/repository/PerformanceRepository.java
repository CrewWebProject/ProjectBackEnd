package com.project.crewwebproject.v1_api.performance.repository;

import com.project.crewwebproject.v1_api.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance , Long> {
}
