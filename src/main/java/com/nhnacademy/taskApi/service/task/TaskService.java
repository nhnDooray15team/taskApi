package com.nhnacademy.taskApi.service.task;

import com.nhnacademy.taskApi.dto.task.TaskRequest;
import com.nhnacademy.taskApi.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {
    void createTask(Long projectId, TaskRequest taskRequest);

    TaskResponse getProjectTask(Long projectId, Long taskId);

    List<TaskResponse> getProjectTaskList(Long projectId);

    void deleteProjectTask(Long projectId, Long taskId);

    TaskResponse modifyProjectTask(Long projectId, Long taskId, TaskRequest request);
}
