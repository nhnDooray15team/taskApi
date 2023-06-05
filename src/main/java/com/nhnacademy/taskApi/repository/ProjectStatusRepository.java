package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
