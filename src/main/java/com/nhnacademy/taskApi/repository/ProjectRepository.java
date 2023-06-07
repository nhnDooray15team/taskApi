package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {

}
