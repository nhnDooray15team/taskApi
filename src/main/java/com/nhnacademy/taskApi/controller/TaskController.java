package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.task.TaskResponse;
import com.nhnacademy.taskApi.dto.task.TaskTagCommentResponse;
import com.nhnacademy.taskApi.dto.task.request.TaskRequest;
import com.nhnacademy.taskApi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/projects/{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@PathVariable Long projectId,
                             @RequestBody @Valid TaskRequest request) {
        taskService.createTask(projectId, request);

    }

    @Transactional(readOnly = true)
    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    public TaskTagCommentResponse getProjectTask(@PathVariable Long projectId,
                                                 @PathVariable Long taskId) {
        return taskService.getProjectTask(projectId, taskId);
    }

    @Transactional(readOnly = true)
    @GetMapping("/projects/{projectId}/tasks")
    public List<TaskResponse> getProjectTaskList(@PathVariable Long projectId) {
        return taskService.getProjectTaskList(projectId);
    }

    @DeleteMapping("/projects/projectId/tasks/{taskId}")
    public void deleteProjectTask(@PathVariable Long taskId) {
        taskService.deleteProjectTask(taskId);
    }

    @PatchMapping("/projects/{projectId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProjectTask(@PathVariable Long projectId,
                                          @PathVariable Long taskId,
                                          @RequestBody @Valid TaskRequest request) {

        taskService.modifyProjectTask(projectId, taskId, request);
    }
}
