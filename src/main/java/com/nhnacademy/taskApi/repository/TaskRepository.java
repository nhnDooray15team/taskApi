package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
