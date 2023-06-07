package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {

}
