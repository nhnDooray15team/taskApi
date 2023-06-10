package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.task.TaskRequest;
import com.nhnacademy.taskApi.dto.task.TaskResponse;
import com.nhnacademy.taskApi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/projects/{projectId}/tasks")
    public String createTask(@PathVariable Long projectId,
                             @RequestBody TaskRequest request) {
        taskService.createTask(projectId, request);
        return "{\"result\":\"OK\"}";
    }

    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    public TaskResponse getProjectTask(@PathVariable Long projectId,
                                       @PathVariable Long taskId) {
        return taskService.getProjectTask(projectId, taskId);
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<TaskResponse> getProjectTaskList(@PathVariable Long projectId) {
        return taskService.getProjectTaskList(projectId);
    }

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    public String deleteProjectTask(@PathVariable Long projectId,
                                    @PathVariable Long taskId) {
        taskService.deleteProjectTask(projectId, taskId);
        return "{\"result\":\"OK\"}";
    }

    @PatchMapping("/projects/{projectId}/tasks/{taskId}")
    public TaskResponse updateProjectTask(@PathVariable Long projectId,
                                          @PathVariable Long taskId,
                                          @RequestBody TaskRequest request) {
        return taskService.modifyProjectTask(projectId, taskId, request);
    }
}
