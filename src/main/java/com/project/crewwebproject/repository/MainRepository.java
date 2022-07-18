package com.project.crewwebproject.repository;

import com.project.crewwebproject.domain.MainVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository extends JpaRepository<MainVideo,Long> {
}
