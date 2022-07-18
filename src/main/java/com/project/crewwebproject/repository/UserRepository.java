package com.project.crewwebproject.repository;

import com.project.crewwebproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , String> {
}
