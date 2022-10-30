package com.project.crewwebproject.v1_api.user.repository;

import com.project.crewwebproject.v1_api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
