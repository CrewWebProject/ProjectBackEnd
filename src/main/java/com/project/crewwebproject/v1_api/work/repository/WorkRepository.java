package com.project.crewwebproject.v1_api.work.repository;

import com.project.crewwebproject.v1_api.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
}
