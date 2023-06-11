package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectRequest projectCreateRequest){
        projectService.createProject(projectCreateRequest);

    }

    @PatchMapping("/{projects_no}/status")
    @ResponseStatus(HttpStatus.OK)
    public void modifyStatus(@PathVariable("projects_no") Long projectId ,
                             @RequestBody ProjectModifyRequest projectModifyRequest){
        projectService.modifyProject(projectId, projectModifyRequest);
    }

    @GetMapping("/{user_id}/projects")
    public List<ProjectGetResponse> getProjects(@PathVariable("user_id") String userId){
        return projectService.getAllProjectBy(userId);
    }

}
