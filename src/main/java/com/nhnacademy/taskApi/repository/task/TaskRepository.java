package com.nhnacademy.taskApi.repository.task;

import com.nhnacademy.taskApi.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> , TaskRepositoryCustom {

    Optional<Task> findByProject_ProjectIdAndTaskId(Long projectId, Long taskId);

    Optional<List<Task>> findAllByProject_ProjectId(Long projectID);

//    void deleteByProject_ProjectIdAndTaskId(Long projectId, Long taskId);

    void deleteByTaskId(Long taskId);

}
