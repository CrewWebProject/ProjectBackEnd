package com.project.crewwebproject.v1_api.main.repository;

import com.project.crewwebproject.v1_api.main.domain.Main;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<Main,Long> {
}
